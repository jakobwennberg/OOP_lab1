package gui;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Panel that handles the visual representation of cars and the workshop
 */
public class DrawPanel extends JPanel {
    private final ArrayList<CarWrapper> cars;
    private final ArrayList<CarGarageWrapper<?>> garageList;


    public DrawPanel(int x, int y, ArrayList<CarWrapper> cars, ArrayList<CarGarageWrapper<?>> garageList) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.cars = cars;
        this.garageList = garageList;

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw workshop
        for (CarGarageWrapper<?> garage : garageList) {
            g.drawImage(garage.getGarageImg(), garage.getPos().x, garage.getPos().y, null);
        }

        // Draw all cars
        for (CarWrapper car : cars) {
            g.drawImage(car.getCarImg(), car.getPos().x, car.getPos().y, null);
        }
    }
}