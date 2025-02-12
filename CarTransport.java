import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;


public class CarTransport extends Car implements Ramp<Boolean> {
    private boolean rampUp;
    private Deque<Car> cars;
    // lenOfLoadedCars and MAX_LENGTH means that the deck can be full even though MAX_CARS is not yet reached
    private double lenOfLoadedCars = 0;
    private final int MAX_CARS;
    private final double LOADING_DISTANCE;
    private final double MAX_LENGTH;

    public CarTransport() {
        super(2, 500, Color.BLACK, "CarTransport", 20.0);
        MAX_CARS = 6;
        LOADING_DISTANCE = 1.0;
        MAX_LENGTH = 14;
        rampUp = true;
        cars = new ArrayDeque<>();
    }

    public Deque<Car> getCarsList() {
        return cars;
    }

    public int getNumberOfCars() {
        return cars.size();
    }

    @Override
    public Boolean getRampPos() {
        return rampUp;
    }

    @Override
    public void putRampDown() {
        if (getCurrentSpeed() == 0) {
            rampUp = false;
        } else {
            System.out.println("Be stationary before putting the ramp down");
        }
    }

    @Override
    public void putRampUp() {
        rampUp = true;
    }

    private boolean isNearby(Car car) {
        double dx = Math.abs(getX() - car.getX());
        double dy = Math.abs(getY() - car.getY());
        return Math.sqrt(dx * dx + dy * dy) <= LOADING_DISTANCE;
    }

    public void loadCar(Car car) {
        if (!rampUp && cars.size() < MAX_CARS && getCurrentSpeed() == 0 && car.getCurrentSpeed() == 0 && !cars.contains(car) && !(car instanceof CarTransport) && isNearby(car) && lenOfLoadedCars + car.getLength() <= MAX_LENGTH) {
            lenOfLoadedCars += car.getLength();
            cars.push(car);
            car.setX(getX());  // Set initial position
            car.setY(getY());
        }
    }

    public void unloadCar() {
        if (!rampUp && getCurrentSpeed() == 0 && !cars.isEmpty()) {
            Car car = cars.pop(); // LIFO
            lenOfLoadedCars -= car.getLength();

            switch (getDirection()) {
                case 0: // forward
                    car.setX(getX());
                    car.setY(getY() - LOADING_DISTANCE);
                    break;
                case 90:  // right
                    car.setX(getX() - LOADING_DISTANCE);
                    car.setY(getY());
                    break;
                case 180:  // backward
                    car.setX(getX());
                    car.setY(getY() + LOADING_DISTANCE);
                    break;
                case 270:  // left
                    car.setX(getX() + LOADING_DISTANCE);
                    car.setY(getY());
                    break;
            }
        } else {
            System.out.println("The deck is empty, no cars to unload");
        }
    }

    @Override
    public void gas(double amount) {
        if (rampUp) {
            super.gas(amount);
        } else {
            System.out.println("Put the ramp up before moving");
        }
    }

    @Override
    public void startEngine() {
        if (rampUp) {
            super.startEngine();
        } else {
            System.out.println("Put the ramp up before moving");
        }
    }

    @Override
    protected double speedFactor() {
        return this.getEnginePower() * 0.01;
    }

    @Override
    public void move() {
        if (rampUp) {  
            super.move();
            // Update position of all loaded cars to match transport
            for (Car car : cars) {
                car.setX(getX());
                car.setY(getY());
            }
        } else {
            System.out.println("Put the ramp up before moving");
        }

    }
}
