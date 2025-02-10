import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    private final ArrayList<Car> carList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        carList.addAll(Arrays.asList(new Volvo240(), new Saab95(), new Scania(), new CarTransport()));
    }

    @Test
    void testCarMovement() {
        for (Car car : carList){
            car.startEngine();
            car.move();
            // testing if they are the same (expected, actual, delta)
            Assertions.assertEquals(0.1, car.getY(), 0.01);
        }
    }

    @Test
    void testGasAndBreak() {
        for (Car car : carList){
            // Tests if currentSpeed >= 0
            car.brake(1);
            assertEquals(0, car.getCurrentSpeed());

            // Tests if Gas increases speed
            car.startEngine();
            car.gas(0.5);
            double oldSpeed = car.getCurrentSpeed();
            car.gas(0.5);
            assertTrue(car.getCurrentSpeed() > oldSpeed);

            // Tests if Brake decreases speed
            oldSpeed = car.getCurrentSpeed();
            car.brake(0.5);
            assertTrue(car.getCurrentSpeed() < oldSpeed);
        }
    }

    @Test
    void testMax() {
        for (Car car : carList){
            car.startEngine();

            // Gets up to max speed
            while (car.getCurrentSpeed() < car.getEnginePower()) {
                car.gas(1);
            }

            // then Gas again to see if we go above EnginePower (== max speeed)
            car.gas(1);
            Assertions.assertEquals(car.getCurrentSpeed(), car.getEnginePower());
        }
    }

    @Test
    void testTurn() {
        for (Car car : carList){
            car.turnRight();
            Assertions.assertEquals(90, car.getDirection());
            car.turnLeft();
            Assertions.assertEquals(0, car.getDirection());
        }
    }

    @Test
    void testGasInvalidLow() {
        // Tests if Gas makes the throw if we go below 0
        for (Car car : carList){
            assertThrows(IllegalArgumentException.class, () -> car.gas(-0.1));
            assertThrows(IllegalArgumentException.class, () -> car.gas(-1));
        }
    }

    @Test
    void testGaInvalidHigh() {
        // Tests if Gas makes the throw if we go above 1
        for (Car car : carList){
            assertThrows(IllegalArgumentException.class, () -> car.gas(2));
            assertThrows(IllegalArgumentException.class, () -> car.gas(1.1));
        }
    }

}

