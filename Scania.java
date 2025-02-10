import java.awt.Color;

public class Scania extends Car {
    private double platformBody;
    private final static double MAX_ANGLE = 70;
    private final static double MIN_ANGLE = 0;

    public Scania() {
        super(2, 400, Color.BLUE, "Scania", 15.0);
        this.platformBody = 0;
        
    }

    public double getPlatformBody() {
        return platformBody;
    }

    public void raisePlatform(double amount) {
        if (getCurrentSpeed() == 0) {
            platformBody = Math.min(platformBody + amount, MAX_ANGLE);
        } else {
            System.out.println("Must be stationary before raising platform");
        }
    }

    public void lowerPlatform(double amount) {
        platformBody = Math.max(platformBody - amount, MIN_ANGLE);
    }

    @Override
    public void startEngine() {
        if (platformBody == 0) {
            super.startEngine();
        } else {
            System.out.println("Lower platform before moving");
        }
    }

    @Override
    public void gas(double amount) {
        if (platformBody == 0) {
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
