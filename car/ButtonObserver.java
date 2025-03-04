package car;

public interface ButtonObserver {

    void startEngine();
    void stopEngine();
    void setX(double x);
    void setY(double y);
    void gas(double amount);
    void brake(double amount);


}
