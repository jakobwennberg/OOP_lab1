package gui;

import car.ObservingCar;
import car.CarFactory;

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
        ArrayList<ObservingCar> cars = CarFactory.createInitialCars();
        
        // Create the main frame
        // "View" in MVC
        DrawFrame frame = new DrawFrame("CarSim 1.0", cars);
        
        // Create timer listener
        // "Controller" in MVC
        TimerListener timerListener = new TimerListener(cars, frame);
        
        // Set up and start the timer
        Timer timer = new Timer(DELAY, timerListener);
        timer.start();
    }
}