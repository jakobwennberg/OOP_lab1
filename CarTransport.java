import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;


public class CarTransport extends Car {
    private boolean rampUp;
    private Deque<Car> cars;
    // private double lenOfCar;
    private final static int MAX_CARS = 5;
    private final static double LOADING_DISTANCE = 1.0;

    public CarTransport() {
        super(2, 500, Color.BLACK, "CarTransport");
        this.rampUp = true;
        this.cars = new ArrayDeque<>();
    }

    public boolean getRampUp() {
        return rampUp;
    }

    public Deque<Car> getCarsList() {
        return cars;
    }

    public int getNumberOfCars() {
        return cars.size();
    }

    public void putRampDown() {
        if (getCurrentSpeed() == 0) {
            rampUp = false;
        }
    }

    public void putRampUp() {
        rampUp = true;
    }

    private boolean isNearby(Car car) {
        double dx = Math.abs(getX() - car.getX());
        double dy = Math.abs(getY() - car.getY());
        return Math.sqrt(dx * dx + dy * dy) <= LOADING_DISTANCE;
    }

    public void loadCar(Car car) {
        if (!rampUp && cars.size() < MAX_CARS && getCurrentSpeed() == 0 && car.getCurrentSpeed() == 0 && !cars.contains(car) && !(car instanceof CarTransport) && isNearby(car)) {
            cars.push(car);
            car.setX(getX());  // Set initial position
            car.setY(getY());
        }
    }

    public void unloadCar() {
        if (!rampUp && getCurrentSpeed() == 0 && !cars.isEmpty()) {
            Car car = cars.pop(); // LIFO

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
        }
    }

    @Override
    public void gas(double amount) {
        if (rampUp) {
            super.gas(amount);
        }
    }

    @Override
    public void startEngine() {
        if (rampUp) {
            super.startEngine();
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
        }
    }
}

// Add len of car to car class and then update every other class. Then update Transport so that it only adds cars a certain length??