import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class RampTest {
    private final Scania scania = new Scania();

    @Test
    void testRamp() {
        scania.putRampDown();
        double oldAngle = scania.getRampPos();
        scania.putRampUp();
        assertNotSame(scania.getRampPos(), oldAngle);
    }
}

