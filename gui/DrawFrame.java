package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class DrawFrame extends JFrame {
    private static final int X = 800;
    private static final int Y = 800;

    private final ControllerPanel controllerPanel;
    public DrawPanel drawPanel;


    public DrawFrame(String frameName, ArrayList<CarWrapper> cars, ArrayList<CarGarageWrapper<?>> garageList) {
        this.drawPanel = new DrawPanel(X, Y-240,cars, garageList);
        this.controllerPanel = new ControllerPanel(X, Y, cars);
        initComponents(frameName);
    }

    private void initComponents(String title) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(X, Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // Add draw panel for car visualization
        this.add(drawPanel);
        // Add controller panel for car control
        this.add(controllerPanel);


        // Frame setup
        this.pack();
        // Center the frame on screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}