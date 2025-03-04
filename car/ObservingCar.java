package car;

public interface ObservingCar {

    void startEngine();
    void stopEngine();
    void setX(double x);
    void setY(double y);
    void gas(double amount);
    void brake(double amount);
    double getX();
    double getY();
    void move();
    void turnLeft();
    void turnRight();
    int getDirection();
    String getCarType();


}
