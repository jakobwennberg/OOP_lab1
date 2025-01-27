import java.awt.*;


public abstract class Car implements Movable {
   private final int nrDoors;
   private final double enginePower;
   private double currentSpeed;
   private Color color;
   private double x = 0; // cars x pos
   private double y = 0; // cars y pos
   private int direction = 0; // 0: forward, 90: right, 180: backward, 270: left
  
   // Constructor
   public Car(int nrDoors, double enginePower, Color color) {
       this.nrDoors = nrDoors;
       this.enginePower = enginePower;
       this.currentSpeed = 0;
       this.color = color;

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

   // Setter
   public void setColor(Color color) {
       this.color = color;
   }


   public void startEngine() {
       this.currentSpeed = 0.1;
   }


   public void stopEngine() {
       this.currentSpeed = 0;
   }


   public void gas(double amount) {
       if (amount < 0 || amount > 1) {
           throw new IllegalArgumentException("Amount must be in [0, 1]");
       }
       double newSpeed = this.currentSpeed + speedFactor() * amount; // Calc new speed
       // Assign the new speed if it does not exceed the engine-power
       this.currentSpeed = Math.min(newSpeed, this.enginePower); // Cap the speed
   }

   // Override from movable
   @Override
   public void move() {
       if (this.direction == 0) { // Uppåt
           this.y += getCurrentSpeed();
       } else if (this.direction == 90) { // Höger
           this.x += getCurrentSpeed();
       } else if (this.direction == 180) { // Neråt
           this.y -= getCurrentSpeed();
       } else if (this.direction == 270) { // Vänster
           this.x -= getCurrentSpeed();
       }
   }

   // Override from movable
   @Override
   public void turnLeft() {
       this.direction = (this.direction + 270) % 360; // add 270, instead of -90, to avoid negative values
   }

   // Override from movable
   @Override
   public void turnRight() {
       this.direction = (this.direction + 90) % 360;
   }

    // abstract method to be implemented in subclass
    public abstract double speedFactor();
}





