package car;

import java.util.ArrayList;

public class CarFactory {
    
    public static Car createVolvo240() {
        return new Volvo240();
    }
    
    public static Car createSaab95() {
        return new Saab95();
    }
    
    public static Car createScania() {
        return new Scania();
    }
    
    public static Car createCarTransport() {
        return new CarTransport();
    }
    
    public static ArrayList<Car> createInitialCars() {
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(createVolvo240());  // Y = 0
        cars.add(createSaab95());    // Y = 100
        cars.add(createScania());    // Y = 200
        
        // Set initial Y positions
        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).setY(i * 100);
        }
        
        return cars;
    }
}