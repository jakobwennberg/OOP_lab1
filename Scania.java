import java.awt.Color;

public class Scania extends Car implements Ramp<Double> {
    private double rampBody;
    private final static double MAX_ANGLE = 70;
    private final static double MIN_ANGLE = 0;

    public Scania() {
        super(2, 400, Color.BLUE, "Scania", 15.0);
        this.rampBody = MIN_ANGLE;
    }

    @Override
    public Double getRampPos() {
        return rampBody;
    }

    @Override
    public void putRampDown() {
        rampBody = MIN_ANGLE;
    }

    @Override
    public void putRampUp() {
        rampBody = MAX_ANGLE;
    }


    public void raiseRamp(double amount) {
        if (getCurrentSpeed() == 0) {
            rampBody = Math.min(rampBody + amount, MAX_ANGLE);
        } else {
            System.out.println("Must be stationary before raising platform");
        }
    }

    public void lowerRamp(double amount) {
        rampBody = Math.max(rampBody - amount, MIN_ANGLE);
    }

    @Override
    public void startEngine() {
        if (rampBody == MIN_ANGLE) {
            super.startEngine();
        } else {
            System.out.println("Lower platform before moving");
        }
    }

    @Override
    public void gas(double amount) {
        if (rampBody == MIN_ANGLE) {
            super.gas(amount);
        } else {
            System.out.println("Lower platform before moving");
        }
    }

    @Override
    protected double speedFactor() {
        return this.getEnginePower() * 0.01;
    }

}
