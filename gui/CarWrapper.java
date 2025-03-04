package gui;

import car.Car;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CarWrapper {
    private final Car car;
    private final BufferedImage carImg;

    public CarWrapper(Car car, double x, double y, BufferedImage img){
        this.car = car;
        this.car.setX(x);
        this.car.setY(y);
        this.carImg = img;
    }

    public Car getCar() {
        return car;
    }

    public Point getPos() {
        return new Point((int) Math.round(car.getX()), (int) Math.round(car.getY()));
    }

    public BufferedImage getCarImg() {
        return carImg;
    }
}
