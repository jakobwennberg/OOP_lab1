import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CarTest {
    private Volvo240 volvo;
    private Saab95 saab;
    private Scania scania;
    private CarTransport carTransport;

    @BeforeEach
    void setUp() {
        volvo = new Volvo240();
        saab = new Saab95();
        scania = new Scania();
        carTransport = new CarTransport();
    }

    @Test
    void testCarMovement() {
        volvo.startEngine();
        volvo.move();
        // testing if they are the same (expected, actual, delta)
        assertEquals(0.1, volvo.getY(), 0.01);
    }

    @Test
    void testGasAndBreak() {
        saab.setTurboOn();

        // Tests if currentSpeed >= 0
        saab.brake(1);
        assertTrue(saab.getCurrentSpeed() >= 0);

        // Tests if Gas increases speed
        saab.startEngine();
        saab.gas(0.5);
        double oldSpeed = saab.getCurrentSpeed();
        saab.gas(0.5);
        assertTrue(saab.getCurrentSpeed() > oldSpeed);

        // Tests if Brake decreases speed
        oldSpeed = saab.getCurrentSpeed();
        saab.brake(0.5);
        assertTrue(saab.getCurrentSpeed() < oldSpeed);
    }

    @Test
    void testMax() {
        saab.setTurboOn();
        saab.startEngine();

        // Gets up to max speed
        while (saab.getCurrentSpeed() != saab.getEnginePower()) {
            saab.gas(1);
        }

        // then Gas again to see if we go above EnginePower (== max speeed)
        saab.gas(1);
        assertEquals(saab.getCurrentSpeed(), saab.getEnginePower());
    }

    @Test
    void testTurn() {
        volvo.turnRight();
        assertEquals(90, volvo.getDirection());
        volvo.turnLeft();
        assertEquals(0, volvo.getDirection());

        saab.turnRight();
        assertEquals(90, saab.getDirection());
        saab.turnLeft();
        assertEquals(0, saab.getDirection());
    }

    @Test
    void testGasInvalidLow() {
        // Tests if Gas makes the throw if we go below 0
        assertThrows(IllegalArgumentException.class, () -> volvo.gas(-0.1));
        assertThrows(IllegalArgumentException.class, () -> saab.gas(-1));
    }

    @Test
    void testGaInvalidHigh() {
        // Tests if Gas makes the throw if we go above 1
        assertThrows(IllegalArgumentException.class, () -> volvo.gas(2));
        assertThrows(IllegalArgumentException.class, () -> saab.gas(1.1));
    }

    @Test
    void testRaisePlatformBodyStationary() {
        Scania scania = new Scania();

        // Test raising platform when stationary
        scania.raisePlatform(30);
        assertEquals(30, scania.getPlatformBody(), 0.01);
    }

    @Test
    void testMaxAngle() {
        // Test exceeding maximum angle
        scania.raisePlatform(80);
        assertEquals(70, scania.getPlatformBody(), 0.01);
    }

    @Test
    void testPlatformWhileMoving() {
        // Test platform operation while moving
        scania.startEngine();
        scania.gas(0.5);
        scania.raisePlatform(30);  // Should not raise while moving
        assertEquals(0, scania.getPlatformBody(), 0.01);
    }

    @Test
    void testLoadingAndUnloading() {
        // Set positions close to each other
        volvo.setX(carTransport.getX() + 0.5);
        volvo.setY(carTransport.getY());
        
        carTransport.putRampDown();
        carTransport.loadCar(volvo);
        assertEquals(1, carTransport.getNumberOfCars());
        
        // Check position synchronization
        carTransport.putRampUp();
        carTransport.move();
        assertEquals(carTransport.getX(), volvo.getX(), 0.01);
        assertEquals(carTransport.getY(), volvo.getY(), 0.01);
        
        // Test unloading
        carTransport.putRampDown();
        carTransport.unloadCar();
        assertEquals(0, carTransport.getNumberOfCars());
    }
}

