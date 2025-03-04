package gui;

import car.Car;
import car.CarGarage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CarGarageWrapper<T extends Car> {
    private final CarGarage<T> garage;
    private final Class<T> carType;
    private final BufferedImage carImg;
    private final int x;
    private final int y;
    private final int width;
    private final int height;


    public CarGarageWrapper(CarGarage<T> garage, Class<T> type, int x, int y, int width, int height, BufferedImage img){
        this.garage = garage;
        this.carType = type;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.carImg = img;
    }

    public CarGarage<T> getGarage() {
        return garage;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getPos() {
        return new Point(x, y);
    }

    public BufferedImage getGarageImg() {
        return carImg;
    }

    public Class<T> getCarType() {
        return carType;
    }
}
