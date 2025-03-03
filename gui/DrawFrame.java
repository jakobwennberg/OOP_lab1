package gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

import car.Car;


 // Main frame for the car simulation GUI
 // Contains all panels and displays the cars
 
public class DrawFrame extends JFrame {
    private static final int X = 800;
    private static final int Y = 800;

    public DrawPanel drawPanel;
    private Buttons buttons;
    private ButtonFunctions buttonFunctions;
    
    private JPanel controlPanel = new JPanel();
    private JPanel gasPanel = new JPanel();
    private JSpinner gasSpinner = new JSpinner();
    private int gasAmount = 0;
    private JLabel gasLabel = new JLabel("Amount of gas");

    public DrawFrame(String frameName, ArrayList<Car> cars) {
        this.buttons = new Buttons();
        this.buttonFunctions = new ButtonFunctions(cars);
        this.drawPanel = new DrawPanel(X, Y-240);
        initComponents(frameName);
    }

    private void initComponents(String title) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(X, Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // Add draw panel for car visualization
        this.add(drawPanel);

        // Configure gas spinner
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        // Set up gas panel
        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);
        this.add(gasPanel);

        // Set up control panel
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

        // Frame setup
        this.pack();
        
        // Center the frame on screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}