package gui;

import java.util.ArrayList;


public class CarLogic {
    // Boundaries for car movement
    public static final int X_BOUNDARY = 700;
    public static final int Y_BOUNDARY = 500;


    public static void moveCar(CarWrapper car, ArrayList<CarGarageWrapper<?>> garageList) {
        car.getCar().move();

        // Check for workshop collision
        for (CarGarageWrapper<?> garageWrapper : garageList) {
            if (garageWrapper.getCarType().isInstance(car.getCar())) {
                if (isCollidingWithWorkshop(car.getPos().x, car.getPos().y, garageWrapper)) {
                    if (garageWrapper.getCarType().isInstance(car.getCar())) {
                        // Load the Car into workshop
                        car.getCar().setX(garageWrapper.getPos().x);
                        car.getCar().setY(garageWrapper.getPos().y);
                        car.getCar().stopEngine();
                        return;
                    }
                }
            }
        }

        double oldX = car.getCar().getX();
        double oldY = car.getCar().getY();

        // Check X boundary collisions
        if (car.getCar().getX() > X_BOUNDARY || car.getCar().getX() < 0) {
            // Reset position to boundary
            car.getCar().setX(oldX);
            // Turn around
            if (car.getCar().getDirection() == 90) {
                car.getCar().turnLeft();
                car.getCar().turnLeft();
            } else {
                car.getCar().turnRight();
                car.getCar().turnRight();
            }
        }

        // Check Y boundary collisions
        if (car.getCar().getY() > Y_BOUNDARY || car.getCar().getY() < 0) {
            // Reset position to boundary
            car.getCar().setY(oldY);
            // Turn around
            if (car.getCar().getDirection() == 0) {
                car.getCar().turnLeft();
                car.getCar().turnLeft();
            } else {
                car.getCar().turnRight();
                car.getCar().turnRight();
            }
        }
    }

    private static boolean isCollidingWithWorkshop(int carX, int carY, CarGarageWrapper<?> garage) {
        return carX >= garage.getPos().x && carX <= garage.getPos().x + garage.getWidth() &&
                carY >= garage.getPos().y && carY <= garage.getPos().y + garage.getHeight();
    }
}
