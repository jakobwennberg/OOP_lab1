package car;


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

    public static <T extends Car> CarGarage<T> createCarGarage(int maxCapacity) {
        return new CarGarage<>(maxCapacity);
    }
}
