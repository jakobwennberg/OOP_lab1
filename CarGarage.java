import java.util.ArrayList;

public class CarGarage<T extends Car> { // Specifikt vad T är bestäms då vi specifierar den statiska typen (???)
                                    // för ett nytt garage. Till exempel Garage<Volvol240> volvoGarage = new Garage<>(5)
    private final int maxCapacity;
    private final ArrayList<T> cars; // Endast bilar av typen T.

    public CarGarage(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.cars = new ArrayList<>();
    }

    public void leaveCar(T car) {   // Klassens implementation gör så att fel biltyp aldrig kan lämnas in.
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
