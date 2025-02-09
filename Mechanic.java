import java.util.ArrayList;

public class Mechanic<CarType extends Car> {
    private final int MAX_CARS;
    private final String FRANCHISE;
    private ArrayList<CarType> storage;


    public Mechanic(int maxCars, String franchise){
        this.MAX_CARS = maxCars;
        this.FRANCHISE = franchise;
    }


    public int getMaxStorage() {
        return MAX_CARS;
    }


    public int getCurrentStorage() {
        return storage.size();
    }


    public void storeCar(CarType car) {
        if (getCurrentStorage() < getMaxStorage()){
            storage.add(car);
        } else {
            System.out.println("The shop is full");
        }
    }


    public void removeCar(CarType car) {
        if (getCurrentStorage() > 0){
            storage.remove(car);
        } else {
            System.out.println("There are no cars to remove");
        }
    }

    public String getFranchise() {
        return FRANCHISE;
    }
}


class testMechanic {
    public static void main(String[] args) {
        // Test by changing the parameters

        // Mechanic<Car> myMechanic = new Mechanic<>(10,"Bosses Bil");
        // Mechanic<Object> myMechanic = new Mechanic<>(10,"Bosses Bil");
        // Mechanic<Volvo240> myMechanic = new Mechanic<>(10,"Bosses Bil");
        Mechanic<Saab95> myMechanic = new Mechanic<>(10,"Bosses Bil");

        // myMechanic.storeCar(new Volvo240());
        myMechanic.storeCar(new Saab95());
    }
}