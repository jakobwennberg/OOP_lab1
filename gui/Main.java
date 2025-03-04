package gui;

import javax.swing.Timer;
import java.util.ArrayList;

/**
 * Main class for the car simulation application
 */
public class Main {
    private static final int DELAY = 50;

    public static void main(String[] args) {
        // Create cars using the CarFactory
        // These car objects are the "Model" in MVC
        ArrayList<CarWrapper> cars = new ArrayList<>();
        cars.add(CarWrapperFactory.createVolvo240(0,0));
        cars.add(CarWrapperFactory.createSaab95(0,100));
        cars.add(CarWrapperFactory.createScania(0,200));

        ArrayList<CarGarageWrapper<?>> garageList = new ArrayList<>();
        garageList.add(CarWrapperFactory.createVolvo240Garage(0,300,100,100,5));


        // Create the main frame
        // "View" in MVC
        DrawFrame frame = new DrawFrame("CarSim 1.0", cars, garageList);
        
        // Create timer listener
        // "Controller" in MVC
        TimerListener timerListener = new TimerListener(cars, garageList, frame);
        
        // Set up and start the timer
        Timer timer = new Timer(DELAY, timerListener);
        timer.start();
    }
}