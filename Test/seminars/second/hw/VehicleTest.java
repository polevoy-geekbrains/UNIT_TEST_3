package seminars.second.hw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seminars.second.simple_shopping_cart.Cart;
import seminars.second.simple_shopping_cart.Shop;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    private Car car;
    private Motorcycle motorcycle;

    @BeforeEach
    void setup() {
        car = new Car("Honda", "Civic", 2006);
        motorcycle = new Motorcycle("Yamaha", "Bolt", 2017);
    }

    @Test
    public void testCarIsInstanceOfVehicle() {
        assertTrue(car instanceof Vehicle);
    }

    @Test
    public void testCarHasFourWheels() {
        assertEquals(car.getNumWheels(), 4);
    }

    @Test
    public void testMotorcycleHasTwoWheels() {
        assertEquals(motorcycle.getNumWheels(), 2);
    }

    @Test
    public void testCarSpeed() {
        car.testDrive();
        assertEquals(car.getSpeed(), 60);
    }

    @Test
    public void testMotorcycleSpeed() {
        motorcycle.testDrive();
        assertEquals(motorcycle.getSpeed(), 75);
    }

    @Test
    public void testCarPark() {
        car.testDrive();
        car.park();
        assertEquals(car.getSpeed(), 0);
    }

    @Test
    public void testMotorcyclePark() {
        motorcycle.testDrive();
        motorcycle.park();
        assertEquals(motorcycle.getSpeed(), 0);
    }
}