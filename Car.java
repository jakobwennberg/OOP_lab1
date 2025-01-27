
import java.awt.*;

public abstract class Car implements Movable {
   // Gemensamma attribut som alla i Car obj måste ha
   private int nrDoors;
   private double enginePower;
   private double currentSpeed;
   private Color color;
   private double x = 0; // Bilens position på x-axeln
   private double y = 0; // Bilens position på y-axeln
   private int direction = 0; // 0: Upp, 90: Höger, 180: Ner, 270: Vänster
  
   // Konstruktor för att göra dessa attribut eller vafan man säger
   public Car(int nrDoors, double enginePower, Color color) {
       this.nrDoors = nrDoors;
       this.enginePower = enginePower;
       this.currentSpeed = 0;
       this.color = color;


   }
   public double getX() {
       return x;
   }

   // Vanliga getters som i python
   public double getY() {
       return y;
   }


   public int getDirection() {
       return direction;
   }


   public void startEngine() {
       currentSpeed = 0.1;
   }


   public void stopEngine() {
       currentSpeed = 0;
   }


   public int getNrDoors() {
       return nrDoors;
   }


   public double getEnginePower() {
       return enginePower;
   }


   public double getCurrentSpeed() {
       return currentSpeed;
   }


   public Color getColor() {
       return color;
   }

   // Metod som alla bilar delar
   public void setColor(Color color) {
       this.color = color;
   }

   // Abstrakt metod som måste implementeras i subklasser
   public abstract double speedFactor();


   public void gas(double amount) {
       if (amount < 0 || amount > 1) {
           throw new IllegalArgumentException("Amount must be in [0, 1]");
       }
       double newSpeed = currentSpeed + speedFactor() * amount; // Räkna ny speed
       if (newSpeed > enginePower) { // Kolla om nya speeden går över motorkraften
           currentSpeed = enginePower; // Capa speeden
       } else {
           currentSpeed = newSpeed; // Annars så assignar vi nya speeden
       }
   }

   // Här overridar vi från interfacet movable. Switch är som if elif i python
   @Override
   public void move() {
       switch (direction) {
           case 0: // Uppåt
               y += getCurrentSpeed();
               break;
           case 90: // Höger
               x += getCurrentSpeed();
               break;
           case 180: // Neråt
               y -= getCurrentSpeed();
               break;
           case 270: // Vänster
               x -= getCurrentSpeed();
               break;
       }
   }


   @Override
   public void turnLeft() {
       direction = (direction + 270) % 360; // Lägg till 270 istället för att subtrahera 90 för att undvika negativa värden
   }


   @Override
   public void turnRight() {
       direction = (direction + 90) % 360;
   }






}





