
import java.awt.Color;


public class Volvo240 extends Car {
    // Lägger till en trimfactor. Denna är "final" eftersom den är specifik till klassen
   private final static double trimFactor = 1.25;
  // Ärver attribut från Car
   public Volvo240() {
       super(4, 100, Color.BLACK);
   }

   // Overridar speedfactorn som va abstrakt i Car klassen
   @Override
   public double speedFactor() {
       return getEnginePower() * 0.01 * trimFactor;
   }


}
