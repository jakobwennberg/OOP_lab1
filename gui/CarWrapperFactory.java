package gui;

import car.Car;
import car.Volvo240;
import car.CarFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;


public class CarWrapperFactory {
    private static final BufferedImage volvoImage;
    private static final BufferedImage saabImage;
    private static final BufferedImage scaniaImage;
    private static final BufferedImage transportImage;
    private static final BufferedImage volvoGarageImage;
    static {
        try {
            scaniaImage = ImageIO.read(new File("gui/pics/Scania.jpg"));
            saabImage = ImageIO.read(new File("gui/pics/Saab95.jpg"));
            volvoImage = ImageIO.read(new File("gui/pics/Volvo240.jpg"));
            transportImage = ImageIO.read(new File("gui/pics/VolvoBrand.jpg"));
            volvoGarageImage = ImageIO.read(new File("gui/pics/VolvoBrand.jpg"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static CarWrapper createVolvo240(double x, double y) {
        return new CarWrapper(CarFactory.createVolvo240(), x, y, volvoImage);
    }

    public static CarWrapper createSaab95(double x, double y) {
        return new CarWrapper(CarFactory.createSaab95(), x, y, saabImage);
    }

    public static CarWrapper createScania(double x, double y) {
        return new CarWrapper(CarFactory.createScania(), x, y, scaniaImage);
    }

    public static CarWrapper createCarTransport(double x, double y) {
        return new CarWrapper(CarFactory.createCarTransport(), x, y, transportImage);
    }

    private static <T extends Car> CarGarageWrapper<T> createCarGarage(Class<T> carClass, int x, int y, int width, int height, int maxCapacity) {
        return new CarGarageWrapper<>(CarFactory.createCarGarage((maxCapacity)), carClass, x, y, width, height, volvoGarageImage);
    }

    public static CarGarageWrapper<Volvo240> createVolvo240Garage(int x, int y, int width, int height, int maxCapacity) {
        return createCarGarage(Volvo240.class, x, y, width, height, maxCapacity);
    }
}
