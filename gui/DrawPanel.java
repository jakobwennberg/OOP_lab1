package gui;

import car.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Panel that handles the visual representation of cars and the workshop
 */
public class DrawPanel extends JPanel {
    private ArrayList<BufferedImage> carImages;
    private ArrayList<Point> carPoints;
    private BufferedImage workshopImage;
    private Point workshopPoint;
    private BufferedImage volvoImage;
    private BufferedImage saabImage;
    private BufferedImage scaniaImage;
    private BufferedImage transportImage;

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
            // Load car images using File objects
            volvoImage = ImageIO.read(new File("gui/pics/Volvo240.jpg"));
            saabImage = ImageIO.read(new File("gui/pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(new File("gui/pics/Scania.jpg"));
            
            // Load workshop image
            workshopImage = ImageIO.read(new File("gui/pics/VolvoBrand.jpg"));
            
            // Initialize transportImage (use Volvo as fallback if needed)
            try {
                transportImage = ImageIO.read(new File("gui/pics/CarTransport.jpg"));
            } catch (IOException e) {
                // If CarTransport image doesn't exist, use Volvo as fallback
                transportImage = volvoImage;
            }
            
            // Add initial car images (NOT including workshop)
            carImages.add(volvoImage);
            carImages.add(saabImage);
            carImages.add(scaniaImage);
            
            // Initialize points for each car
            for (int i = 0; i < carImages.size(); i++) {
                carPoints.add(new Point(0, i * 100));
            }
        } catch (IOException ex) {
            System.out.println("Error loading images: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    
    public void moveCar(double x, double y, int index) {
        // Ensure we have enough points for all cars
        while (index >= carPoints.size()) {
            carPoints.add(new Point(0, 0));
        }
        
        Point p = carPoints.get(index);
        p.x = (int) Math.round(x);
        p.y = (int) Math.round(y);

        // Check for workshop collision (only for Volvo)
        if (index == 0) { // Volvo is assumed to be first in our list
            if (isCollidingWithWorkshop(p.x, p.y)) {
                // "Load" the Volvo into workshop by hiding it
                p.x = workshopPoint.x;
                p.y = workshopPoint.y;
            }
        }
    }

    
     // Updates the car images list to match the current number of cars
     
    public void updateCars(ArrayList<Car> cars) {
        // Ensure we have enough images and points for all cars
        while (carImages.size() < cars.size()) {
            // Determine which image to use based on car type
            if (cars.get(carImages.size()) instanceof Volvo240) {
                carImages.add(volvoImage);
            } else if (cars.get(carImages.size()) instanceof Saab95) {
                carImages.add(saabImage);
            } else if (cars.get(carImages.size()) instanceof Scania) {
                carImages.add(scaniaImage);
            } else {
                carImages.add(transportImage);
            }
        }
        
        // Remove excess images if cars have been removed
        while (carImages.size() > cars.size()) {
            carImages.remove(carImages.size() - 1);
        }
        
        // Ensure we have the right number of points
        while (carPoints.size() < cars.size()) {
            carPoints.add(new Point(0, 0));
        }
        
        while (carPoints.size() > cars.size()) {
            carPoints.remove(carPoints.size() - 1);
        }
    }

    private boolean isCollidingWithWorkshop(int carX, int carY) {
        return carX >= workshopPoint.x && carX <= workshopPoint.x + WORKSHOP_WIDTH &&
               carY >= workshopPoint.y && carY <= workshopPoint.y + WORKSHOP_HEIGHT;
    }

    public boolean checkWorkshop(int x, int y) {
        return isCollidingWithWorkshop(x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw workshop 
        g.drawImage(workshopImage, workshopPoint.x, workshopPoint.y, null);
        
        // Draw all cars
        for (int i = 0; i < carImages.size() && i < carPoints.size(); i++) {
            Point p = carPoints.get(i);
            g.drawImage(carImages.get(i), p.x, p.y, null);
        }
    }
}