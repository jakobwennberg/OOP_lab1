package gui;

import car.Car;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


// Timer listener that handles car movement and collision detection
 
public class TimerListener implements ActionListener {
    private final ArrayList<Car> cars;
    private final DrawFrame frame;
    
    // Boundaries for car movement
    public static final int X_BOUNDARY = 700;
    public static final int Y_BOUNDARY = 500;

    public TimerListener(ArrayList<Car> cars, DrawFrame frame) {
        this.cars = cars;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Car car : cars) {
            double oldX = car.getX();
            double oldY = car.getY();

            // Check if the Volvo is in the workshop zone
            if (cars.indexOf(car) == 0 && frame.drawPanel.checkWorkshop((int) oldX,(int) oldY)) {
                car.stopEngine();
            }

            car.move();
            
            // Check X boundary collisions
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
            
            // Check Y boundary collisions
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

            // Update car position in the view
            frame.drawPanel.moveCar(car.getX(), car.getY(), cars.indexOf(car));
        }
        
        // Update the car images to match the current cars
        frame.drawPanel.updateCars(cars);
        frame.drawPanel.repaint();
        }
    }
