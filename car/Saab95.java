package car;

import java.awt.Color;


public class Saab95 extends Car implements Turbo {
    private boolean turboOn;


    public Saab95() {
        super(2, 125, Color.RED, "Saab95", 3.0);
        this.turboOn = false;
    }

    @Override
    public String getCarType(){
        return "Saab95";
    }

    @Override
    public boolean getTruboStatus() {
        return this.turboOn;
    }

    @Override
    public void setTurboOn() {
        this.turboOn = true;
    }

    @Override
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

