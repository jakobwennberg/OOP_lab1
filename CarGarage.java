import java.util.ArrayList;

public class CarGarage<T extends Car> {
    private final int maxCapacity;
    private final ArrayList<T> cars;

    public CarGarage(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.cars = new ArrayList<>();
    }

    public void leaveCar(T car) {
        if (cars.size() >= maxCapacity) {
            throw new IllegalStateException("The garage is full.");
        }
        cars.add(car);
    }

    public T getCar(T car) {
        if (cars.isEmpty()) {
            throw new IllegalStateException("The garage is empty.");
        }
        else if (!cars.contains(car)) {
            throw new IllegalArgumentException("The car isn't in this garage.");
        }
        cars.remove(car);
        return car;
    }

    public int getCurrentCars() {
        return cars.size();
    }
}
