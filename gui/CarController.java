package gui;

import car.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CarController {
    private final int delay = 50;
    private Timer timer;
    private ArrayList<Car> cars;
    CarView frame;
    
    public static final int X_BOUNDARY = 700;  // Boundary for car movement
    public static final int Y_BOUNDARY = 500;  // Boundary for car movement

    public CarController() {
        this.cars = new ArrayList<>();
        timer = new Timer(delay, new TimerListener());
    }

    public static void main(String[] args) {
        CarController cc = new CarController();

        // Create cars with different starting Y positions
        cc.cars.add(new Volvo240());  // Y = 0
        cc.cars.add(new Saab95());    // Y = 100
        cc.cars.add(new Scania());    // Y = 200

        // Set initial Y positions
        for (int i = 0; i < cc.cars.size(); i++) {
            cc.cars.get(i).setY(i * 100);
        }

        cc.frame = new CarView("CarSim 1.0", cc);
        cc.timer.start();
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                double oldX = car.getX();
                double oldY = car.getY();

                // Vi måste stoppa motorn på volvon ifall den är i workshop.
                // Detta görs genon en ny getter i graphics.pics.CarView. Den nya getter gör
                // så att graphics.pics.CarController inte behöver känna till drawPanel
                // och isCollidingWithWorkshop förblir private.
                if (cars.indexOf(car) == 0 && frame.drawPanel.checkWorkshop((int) oldX,(int) oldY)){
                    car.stopEngine();
                }

                car.move();
                
                // Check boundaries and handle collisions
                if (car.getX() > X_BOUNDARY || car.getX() < 0) {
                    // Reset position to boundary
                    car.setX(oldX);
                    // Turn around
                    if (car.getDirection() == 90) {
                        car.turnLeft();
                        car.turnLeft();
                    } else {
                        car.turnRight();
                        car.turnRight();
                    }
                }
                
                if (car.getY() > Y_BOUNDARY || car.getY() < 0) {
                    // Reset position to boundary
                    car.setY(oldY);
                    // Turn around
                    if (car.getDirection() == 0) {
                        car.turnLeft();
                        car.turnLeft();
                    } else {
                        car.turnRight();
                        car.turnRight();
                    }
                }

                // Update view
                frame.drawPanel.moveCar(car.getX(), car.getY(), cars.indexOf(car));
                frame.drawPanel.repaint();
            }
        }
    }

    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }

    void startAllCars() {
        for (Car car : cars) {
            car.startEngine();
        }
    }

    void stopAllCars() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }

    void turboOn() {
        for (Car car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Car car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void liftBed() {
        for (Car car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).putRampUp(70);
            }
        }
    }

    void lowerBed() {
        for (Car car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).putRampDown(70);
            }
        }
    }
}
