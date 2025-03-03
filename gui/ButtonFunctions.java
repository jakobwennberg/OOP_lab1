package gui;

import car.*;
import java.util.ArrayList;
import java.util.Random;


// Class responsible for handling all button actions in the GUI
 
public class ButtonFunctions {
    private ArrayList<Car> cars;
    private static final int MAX_CARS = 10;
    private final Random random = new Random();
    
    public ButtonFunctions(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }

    public void startAllCars() {
        for (Car car : cars) {
            car.startEngine();
        }
    }

    public void stopAllCars() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }

    public void turboOn() {
        for (Car car : cars) {
            if (car instanceof Turbo) {
                ((Turbo) car).setTurboOn();
            }
        }
    }

    public void turboOff() {
        for (Car car : cars) {
            if (car instanceof Turbo) {
                ((Turbo) car).setTurboOff();
            }
        }
    }

    public void liftBed() {
        for (Car car : cars) {
            if (car instanceof Ramp<?>) {
                ((Ramp<?>) car).putRampUp();
            }
        }
    }

    public void lowerBed() {
        for (Car car : cars) {
            if (car instanceof Ramp<?>) {
                ((Ramp<?>) car).putRampDown();
            }
        }
    }
    
    
     // Adds a random car to the simulation if the maximum car limit hasn't been reached
     
    public void addCar() {
        if (cars.size() < MAX_CARS) {
            Car newCar;
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
            newCar.setY(cars.size() * 70 % 400); // Spread cars vertically
            
            cars.add(newCar);
        }
    }
    
    
     // Removes the last car from the simulation if there are any cars
     
    public void removeCar() {
        if (!cars.isEmpty()) {
            cars.remove(cars.size() - 1);
        }
    }
}