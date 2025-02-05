import java.awt.Color;


public class Volvo240 extends Car {
    private final static double trimFactor = 1.25;


    public Volvo240() {
        super(4, 100, Color.BLACK, "Volvo240", 3.0);
    }

    // Override speedFactor from parent
    @Override
    protected double speedFactor() {
        return this.getEnginePower() * 0.01 * trimFactor;
    }

}
