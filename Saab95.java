
import java.awt.Color;


public class Saab95 extends Car {
   private boolean turboOn;


   public Saab95() {
       super(2, 125, Color.RED);
       turboOn = false;
   }
  // Specifika funktioner för saab
   public void setTurboOn() {
       turboOn = true;
   }


   public void setTurboOff() {
       turboOn = false;
   }
     // Overridar speedfactorn som va abstrakt i Car klassen
   @Override
   public double speedFactor() {
       double turbo = turboOn ? 1.3 : 1;
       return turbo * 0.01 * getEnginePower();
   }
}

