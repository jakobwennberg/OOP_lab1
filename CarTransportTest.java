import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarTransportTest {
    private Volvo240 volvo;
    private CarTransport carTransport;

    @BeforeEach
    void setUp() {
        volvo = new Volvo240();
        carTransport = new CarTransport();
    }

    @Test
    void testLoadingAndUnloading() {
        // Set positions close to each other
        volvo.setX(carTransport.getX() + 0.5);
        volvo.setY(carTransport.getY());

        carTransport.putRampDown();
        carTransport.loadCar(volvo);
        Assertions.assertEquals(1, carTransport.getNumberOfCars());

        // Check position synchronization
        carTransport.putRampUp();
        carTransport.move();
        Assertions.assertEquals(carTransport.getX(), volvo.getX());
        Assertions.assertEquals(carTransport.getY(), volvo.getY());

        // Test unloading
        carTransport.putRampDown();
        carTransport.unloadCar();
        Assertions.assertEquals(0, carTransport.getNumberOfCars());
    }
}
