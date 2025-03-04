package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


// Timer listener that handles car movement and collision detection
 
public class TimerListener implements ActionListener {
    private final ArrayList<CarWrapper> cars;
    private final ArrayList<CarGarageWrapper<?>> garageList;
    private final DrawFrame frame;

    public TimerListener(ArrayList<CarWrapper> wCars, ArrayList<CarGarageWrapper<?>> garageList, DrawFrame frame) {
        this.cars = wCars;
        this.garageList = garageList;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (CarWrapper car : cars){
            CarLogic.moveCar(car, garageList);
        }

        // Update the car images to match the current cars
        frame.drawPanel.repaint();
        }
    }
