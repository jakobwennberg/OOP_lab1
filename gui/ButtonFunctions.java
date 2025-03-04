package gui;

import car.CarFactory;
import car.ObservingCar;
import car.Ramp;
import car.Turbo;

import java.util.ArrayList;
import java.util.Random;


// Class responsible for handling all button actions in the GUI
 
public class ButtonFunctions {
    private ArrayList<ObservingCar> ObservingCars = new ArrayList<>();
    private static final int MAX_CARS = 10;
    private final Random random = new Random();
    
    public ButtonFunctions(ArrayList<ObservingCar> cars) {
        this.ObservingCars = cars;
    }

    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (ObservingCar car : ObservingCars) {
            car.gas(gas);
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (ObservingCar car : ObservingCars) {
            car.brake(brake);
        }
    }

    public void startAllCars() {
        for (ObservingCar car : ObservingCars) {
            car.startEngine();
        }
    }

    public void stopAllCars() {
        for (ObservingCar car : ObservingCars) {
            car.stopEngine();
        }
    }

    public void turboOn() {
        for (ObservingCar car : ObservingCars) {
            if (car instanceof Turbo) {
                ((Turbo) car).setTurboOn();
            }
        }
    }

    public void turboOff() {
        for (ObservingCar car : ObservingCars) {
            if (car instanceof Turbo) {
                ((Turbo) car).setTurboOff();
            }
        }
    }

    public void liftBed() {
        for (ObservingCar car : ObservingCars) {
            if (car instanceof Ramp<?>) {
                ((Ramp<?>) car).putRampUp();
            }
        }
    }

    public void lowerBed() {
        for (ObservingCar car : ObservingCars) {
            if (car instanceof Ramp<?>) {
                ((Ramp<?>) car).putRampDown();
            }
        }
    }
    
    
     // Adds a random car to the simulation if the maximum car limit hasn't been reached
     
    public void addCar() {
        if (ObservingCars.size() < MAX_CARS) {
            ObservingCar newCar;
            int type = random.nextInt(4);

            switch (type) {
                case 0:
                    newCar = CarFactory.createVolvo240();
                    break;
                case 1:
                    newCar = CarFactory.createSaab95();
                    break;
                case 2:
                    newCar = CarFactory.createScania();
                    break;
                default:
                    newCar = CarFactory.createCarTransport();
                    break;
            }
            
            // Set initial position
            newCar.setY(ObservingCars.size() * 70 % 400); // Spread ObservingCars vertically
            
            ObservingCars.add(newCar);
            System.out.println(ObservingCars);
        }
    }
    
    
     // Removes the last car from the simulation if there are any ObservingCars
     
    public void removeCar() {
        if (!ObservingCars.isEmpty()) {
            ObservingCars.remove(ObservingCars.size() - 1);
        }
    }
}