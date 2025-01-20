
import java.awt.Color;


public class Volvo240 extends Car {
   private final static double trimFactor = 1.25;
  
   public Volvo240() {
       super(4, 100, Color.BLACK);
   }


   @Override
   public double speedFactor() {
       return getEnginePower() * 0.01 * trimFactor;
   }


}
