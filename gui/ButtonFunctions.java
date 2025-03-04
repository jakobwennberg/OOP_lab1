package gui;

import car.Ramp;
import car.Turbo;
import java.util.ArrayList;
import java.util.Random;


// Class responsible for handling all button actions in the GUI
 
public class ButtonFunctions {
    private final ArrayList<CarWrapper> cars;
    private static final int MAX_CARS = 10;
    private final Random random = new Random();
    
    public ButtonFunctions(ArrayList<CarWrapper> wCars) {
        this.cars = wCars;
    }

    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (CarWrapper car : cars) {
            car.getCar().gas(gas);
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (CarWrapper car : cars) {
            car.getCar().brake(brake);
        }
    }

    public void startAllCars() {
        for (CarWrapper car : cars) {
            car.getCar().startEngine();
        }
    }

    public void stopAllCars() {
        for (CarWrapper car : cars) {
            car.getCar().stopEngine();
        }
    }

    public void turboOn() {
        for (CarWrapper car : cars) {
            if (car.getCar() instanceof Turbo) {
                ((Turbo) car.getCar()).setTurboOn();
            }
        }
    }

    public void turboOff() {
        for (CarWrapper car : cars) {
            if (car.getCar() instanceof Turbo) {
                ((Turbo) car.getCar()).setTurboOff();
            }
        }
    }

    public void liftBed() {
        for (CarWrapper car : cars) {
            if (car.getCar() instanceof Ramp<?>) {
                ((Ramp<?>) car.getCar()).putRampUp();
            }
        }
    }

    public void lowerBed() {
        for (CarWrapper car : cars) {
            if (car.getCar() instanceof Ramp<?>) {
                ((Ramp<?>) car.getCar()).putRampDown();
            }
        }
    }

     // Adds a random car to the simulation if the maximum car limit hasn't been reached
    public void addCar() {
        if (cars.size() < MAX_CARS) {
            CarWrapper newCar;
            int type = random.nextInt(3);

            newCar = switch (type) {
                case 0 -> CarWrapperFactory.createVolvo240(0, 0);
                case 1 -> CarWrapperFactory.createSaab95(0, 0);
                default -> CarWrapperFactory.createScania(0, 0);
                //default -> CarWrapperFactory.createCarTransport(0, 0);
            };

            // Set initial position
            newCar.getCar().setY(cars.size() * 70 % 400); // Spread cars vertically
            
            cars.add(newCar);
        }
    }
    
    
     // Removes the last car from the simulation if there are any cars
    public void removeCar() {
        if (!cars.isEmpty()) {
            cars.removeLast();
        }
    }
}