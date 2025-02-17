package car.test;

import car.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarGarageTest {
    private Volvo240 volvo;
    private Saab95 saab;
    private CarGarage<Volvo240> volvo240Garage;
    private CarGarage<Saab95> saab95Garage;
    private CarGarage<Car> Garage;

    @BeforeEach
    void setUp() {
        volvo = new Volvo240();
        saab = new Saab95();
        volvo240Garage = new CarGarage<>(4);
        saab95Garage = new CarGarage<>(4);
        Garage = new CarGarage<>(8);
    }

    @Test
    void testAmountOfCars() {
        volvo240Garage.leaveCar(volvo);
        Assertions.assertEquals(1, volvo240Garage.getCurrentCars());
        volvo240Garage.leaveCar(new Volvo240());
        Assertions.assertEquals(2, volvo240Garage.getCurrentCars());

        //saab95Garage.leaveCar(saab);
        //Garage.leaveCar(volvo);
        //Garage.leaveCar(saab);
    }

    @Test
    void testCorrectCarType() {
        saab95Garage.leaveCar(saab);    //OK
        Garage.leaveCar(new Volvo240());//OK
        Garage.leaveCar(new Saab95());  //OK
        //saab95Garage.leaveCar(volvo);   //INTE OK
    }

    @Test
    void testGarageMaxCapacity() {
        volvo240Garage.leaveCar(new Volvo240());
        volvo240Garage.leaveCar(new Volvo240());
        volvo240Garage.leaveCar(new Volvo240());
        volvo240Garage.leaveCar(new Volvo240());
        assertThrows(IllegalStateException.class, () -> volvo240Garage.leaveCar(new Volvo240()));
    }
}
