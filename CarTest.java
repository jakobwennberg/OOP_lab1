import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CarTest {
    @Test
    void testCarMovement() {
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();
        volvo.move();
        // testing if they are the same (expected, actual, delta)
        assertEquals(0.1, volvo.getY(), 0.01);
    }

    @Test
    void testGas() {
        Saab95 saab = new Saab95();
        saab.setTurboOn();
        saab.startEngine();
        saab.gas(0.5);
        assertTrue(saab.getCurrentSpeed() > 0.1);
    }

    @Test
    void testTurn() {
        Volvo240 volvo = new Volvo240();
        volvo.turnRight();
        assertEquals(90, volvo.getDirection());
        volvo.turnLeft();
        assertEquals(0, volvo.getDirection());
    }
}
