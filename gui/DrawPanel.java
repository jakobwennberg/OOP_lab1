package gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DrawPanel extends JPanel {
    private ArrayList<BufferedImage> carImages;
    private ArrayList<Point> carPoints;
    private BufferedImage workshopImage;
    private Point workshopPoint;

    // Dimensions for the workshop collision detection
    private static final int WORKSHOP_WIDTH = 100;
    private static final int WORKSHOP_HEIGHT = 100;

    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        
        // Initialize lists
        carImages = new ArrayList<>();
        carPoints = new ArrayList<>();
        
        // Initialize workshop
        workshopPoint = new Point(0, 300);

        try {
            // Load car images
            carImages.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            carImages.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            carImages.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
            
            // Load workshop image
            workshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
            
            // Initialize points for each car
            for (int i = 0; i < carImages.size(); i++) {
                carPoints.add(new Point(0, i * 100));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void moveCar(double x, double y, int index) {
        if (index >= 0 && index < carPoints.size()) {
            Point p = carPoints.get(index);
            p.x = (int) Math.round(x);
            p.y = (int) Math.round(y);

            // Check for workshop collision (only for Volvo)
            if (index == 0) { // Volvo is always first in our list
                if (isCollidingWithWorkshop(p.x, p.y)) {
                    // "Load" the Volvo into workshop by hiding it
                    p.x = workshopPoint.x;
                    p.y = workshopPoint.y;


                }
            }
        }
    }

    private boolean isCollidingWithWorkshop(int carX, int carY) {

        return carX >= workshopPoint.x && carX <= workshopPoint.x + WORKSHOP_WIDTH &&
               carY >= workshopPoint.y && carY <= workshopPoint.y + WORKSHOP_HEIGHT;
    }

    public boolean checkWorkshop(int x, int y){
        return isCollidingWithWorkshop(x,y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw workshop
        g.drawImage(workshopImage, workshopPoint.x, workshopPoint.y, null);
        
        // Draw all cars
        for (int i = 0; i < carImages.size(); i++) {
            Point p = carPoints.get(i);
            g.drawImage(carImages.get(i), p.x, p.y, null);
        }
    }
}