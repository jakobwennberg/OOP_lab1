package gui;

import javax.swing.*;
import java.awt.*;


// Class responsible for creating and managing all buttons in the GUI
 
public class Buttons {
    private JButton gasButton;
    private JButton brakeButton;
    private JButton turboOnButton;
    private JButton turboOffButton;
    private JButton liftBedButton;
    private JButton lowerBedButton;
    private JButton startButton;
    private JButton stopButton;
    private JButton addCarButton;
    private JButton removeCarButton;
    
    public Buttons() {
        initializeButtons();
        styleButtons();
    }
    
    private void initializeButtons() {
        gasButton = new JButton("Gas");
        brakeButton = new JButton("Brake");
        turboOnButton = new JButton("Saab Turbo on");
        turboOffButton = new JButton("Saab Turbo off");
        liftBedButton = new JButton("Scania Lift Bed");
        lowerBedButton = new JButton("Lower Lift Bed");
        startButton = new JButton("Start all cars");
        stopButton = new JButton("Stop all cars");
        addCarButton = new JButton("Add Car");
        removeCarButton = new JButton("Remove Car");
    }
    
    private void styleButtons() {
        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        
        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        
        addCarButton.setBackground(Color.green);
        addCarButton.setForeground(Color.black);
        
        removeCarButton.setBackground(Color.orange);
        removeCarButton.setForeground(Color.black);
    }
    
    // Getters for all buttons
    public JButton getGasButton() {
        return gasButton;
    }
    
    public JButton getBrakeButton() {
        return brakeButton;
    }
    
    public JButton getTurboOnButton() {
        return turboOnButton;
    }
    
    public JButton getTurboOffButton() {
        return turboOffButton;
    }
    
    public JButton getLiftBedButton() {
        return liftBedButton;
    }
    
    public JButton getLowerBedButton() {
        return lowerBedButton;
    }
    
    public JButton getStartButton() {
        return startButton;
    }
    
    public JButton getStopButton() {
        return stopButton;
    }
    
    public JButton getAddCarButton() {
        return addCarButton;
    }
    
    public JButton getRemoveCarButton() {
        return removeCarButton;
    }
    
    /**
     * Adds all control buttons to the provided panel
     */
    public void addToControlPanel(JPanel controlPanel) {
        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.add(addCarButton, 6);
        controlPanel.add(removeCarButton, 7);
    }
}