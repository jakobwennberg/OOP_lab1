package car;
import java.awt.*;


public abstract class Car implements Movable, ObservingCar {
    private final String modelName;
    private final int nrDoors;
    private final double enginePower;
    private final Color color;
    private final double length;
    private double currentSpeed;
    private double x = 0; // cars x pos
    private double y = 0; // cars y pos
    private int direction = 0; // 0: forward, 90: right, 180: backward, 270: left

    // Constructor
    protected Car(int nrDoors, double enginePower, Color color, String model, double length) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = model;
        this.length = length;
        this.stopEngine();
    }

    // Getters

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public int getDirection() {
        return this.direction;
    }

    public int getNrDoors() {
        return this.nrDoors;
    }

    public double getEnginePower() {
        return this.enginePower;
    }

    public double getCurrentSpeed() {
        return this.currentSpeed;
    }

    public Color getColor() {
        return this.color;
    }

    public String getModelName() {
        return this.modelName;
    }

    public double getLength() {
        return this.length;
    }

    @Override
    public void startEngine() {
        if (this.currentSpeed == 0) {
            this.currentSpeed = 0.1;
        }
    }

    @Override
    public void stopEngine() {
        this.currentSpeed = 0;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void gas(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Amount must be in [0, 1]");
        }
        // Calc new speed, speed can never exceed enginePower
        this.currentSpeed = Math.min(this.getCurrentSpeed() + this.speedFactor() * amount, this.getEnginePower());
    }

    @Override
    public void brake(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Amount must be in [0, 1]");
        }
        // Calc new speed, speed can never go below 0
        this.currentSpeed = Math.max(this.getCurrentSpeed() - this.speedFactor() * amount, 0);
    }

    // Override from movable
    @Override
    public void move() {
        if (this.direction == 0) { // forward
            this.y += getCurrentSpeed();
        } else if (this.direction == 90) { // right
            this.x += getCurrentSpeed();
        } else if (this.direction == 180) { // backward
            this.y -= getCurrentSpeed();
        } else if (this.direction == 270) { // left
            this.x -= getCurrentSpeed();
        }
    }

    // Override from movable
    @Override
    public void turnLeft() {
        this.direction = (this.getDirection() + 270) % 360; // add 270, instead of -90, to avoid negative values
    }

    // Override from movable
    @Override
    public void turnRight() {
        this.direction = (this.getDirection() + 90) % 360;
    }

    // abstract method to be implemented in subclass
    protected abstract double speedFactor();
}

