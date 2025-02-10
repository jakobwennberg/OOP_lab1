import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScaniaTest {
    private Scania scania;

    @BeforeEach
    void setUp() {
        scania = new Scania();
    }

    @Test
    void testRaisePlatformBodyStationary() {
        // Test raising platform when stationary
        scania.raisePlatform(30);
        Assertions.assertEquals(30, scania.getPlatformBody(), 0.01);
    }

    @Test
    void testMaxAngle() {
        // Test exceeding maximum angle
        scania.raisePlatform(80);
        Assertions.assertEquals(70, scania.getPlatformBody(), 0.01);
    }

    @Test
    void testPlatformWhileMoving() {
        // Test platform operation while moving
        scania.startEngine();
        scania.gas(0.5);
        scania.raisePlatform(30);  // Should not raise while moving
        Assertions.assertEquals(0, scania.getPlatformBody(), 0.01);
    }

}
