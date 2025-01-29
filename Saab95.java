import java.awt.Color;


public class Saab95 extends Car {
    private boolean turboOn;


    public Saab95() {
        super(2, 125, Color.RED, "Saab95");
        this.turboOn = false;
    }

    // Specific functions for SAAB
    public void setTurboOn() {
        this.turboOn = true;
    }


    public void setTurboOff() {
        this.turboOn = false;
    }

    // Override speedFactor from parent
    @Override
    protected double speedFactor() {
        double turbo = this.turboOn ? 1.3 : 1;
        return turbo * 0.01 * this.getEnginePower();
    }
}

