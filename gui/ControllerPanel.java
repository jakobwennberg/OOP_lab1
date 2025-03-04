package gui;

import car.ObservingCar;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class ControllerPanel extends JPanel {
    private final ButtonFunctions buttonFunctions;
    private int gasAmount = 0;

    public ControllerPanel (int X, int Y, ArrayList<ObservingCar> cars){
        Buttons buttons = new Buttons();
        this.buttonFunctions = new ButtonFunctions(cars);


        // Configure gas spinner
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
        JSpinner gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        // Set up gas panel
        JPanel gasPanel = new JPanel();
        gasPanel.setLayout(new BorderLayout());
        JLabel gasLabel = new JLabel("Amount of gas");
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);
        this.add(gasPanel);

        // Set up control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2,4));
        buttons.addToControlPanel(controlPanel);
        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        controlPanel.setBackground(Color.CYAN);
        this.add(controlPanel);

        // Add start and stop buttons
        JButton startButton = buttons.getStartButton();
        startButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(startButton);

        JButton stopButton = buttons.getStopButton();
        stopButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(stopButton);

        // Add action listeners for all buttons
        buttons.getGasButton().addActionListener(e -> buttonFunctions.gas(gasAmount));
        buttons.getBrakeButton().addActionListener(e -> buttonFunctions.brake(gasAmount));
        buttons.getTurboOnButton().addActionListener(e -> buttonFunctions.turboOn());
        buttons.getTurboOffButton().addActionListener(e -> buttonFunctions.turboOff());
        buttons.getLiftBedButton().addActionListener(e -> buttonFunctions.liftBed());
        buttons.getLowerBedButton().addActionListener(e -> buttonFunctions.lowerBed());
        buttons.getStartButton().addActionListener(e -> buttonFunctions.startAllCars());
        buttons.getStopButton().addActionListener(e -> buttonFunctions.stopAllCars());
        buttons.getAddCarButton().addActionListener(e -> buttonFunctions.addCar());
        buttons.getRemoveCarButton().addActionListener(e -> buttonFunctions.removeCar());

    }
}
