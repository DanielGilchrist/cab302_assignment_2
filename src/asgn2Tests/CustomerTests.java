package asgn2Tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;
import asgn2Exceptions.CustomerException;

/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer,
 * asgn2Customers.DriverDeliveryCustomer, asgn2Customers.DroneDeliveryCustomer
 * classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer
 * should be used to test the functionality of the asgn2Customers.Customer
 * abstract class.
 * 
 * @author Person A
 * 
 *
 */
public class CustomerTests {
    // private vairables
    Customer pickup;
    Customer droneDelivery;
    Customer driverDelivery;
    Customer pickup2;
    Customer droneDelivery2;
    Customer driverDelivery2;

    @Test
    @Before
    public void getInstances() throws CustomerException {
        pickup = CustomerFactory.getCustomer("PUC", "John Smith", "0123456789", 0, 0);
        droneDelivery = CustomerFactory.getCustomer("DNC", "David Smith", "0123455555", 5, 5);
        driverDelivery = CustomerFactory.getCustomer("DVC", "Tom Smith", "0123456666", 5, 5);
        pickup2 = CustomerFactory.getCustomer("PUC", "John Smith 2nd", "0123456789", 0, 0);
        droneDelivery2 = CustomerFactory.getCustomer("DNC", "David Smith 2nd", "0123455555", 3, 4);
        driverDelivery2 = CustomerFactory.getCustomer("DVC", "Tom Smith 2nd", "0123456666", 2, 9);
    }

    private static double calCulateEuclidianDistance(int x, int y) {
        return Math.sqrt(((double) x * (double) x) + ((double) y * (double) y));
    }

    // -------------------------- Test the objects ---------------------------//
    @Test
    public void testCustomerTypePickup() {
        assertEquals(true, pickup instanceof PickUpCustomer);
        assertEquals(true, pickup2 instanceof PickUpCustomer);
    }

    @Test
    public void testCustomerTypeDroneDelivery() {
        assertEquals(true, droneDelivery instanceof DroneDeliveryCustomer);
        assertEquals(true, droneDelivery2 instanceof DroneDeliveryCustomer);
    }

    @Test
    public void testCustomerTypeDriverDelivery() {
        assertEquals(true, driverDelivery instanceof DriverDeliveryCustomer);
        assertEquals(true, driverDelivery2 instanceof DriverDeliveryCustomer);
    }

    // -------------------------- test distance ---------------------------- //
    @Test
    public void testPickUpDistance() {
        assertTrue(pickup.getDeliveryDistance() == 0.0);
    }

    @Test
    public void testPickUpDistance2nd() {
        assertTrue(pickup2.getDeliveryDistance() == 0.0);
    }

    @Test
    public void testDriverDeliveryDistance() {
        assertTrue(driverDelivery.getDeliveryDistance() == 10.0);
    }

    @Test
    public void testDriverDeliveryDistance2nd() {
        assertTrue(driverDelivery2.getDeliveryDistance() == 11.0);
    }

    @Test
    public void testDroneDeliveryDistance() {
        assertTrue(droneDelivery.getDeliveryDistance() == calCulateEuclidianDistance(5, 5));
    }

    @Test
    public void testDroneDeliveryDistance2nd() {
        assertTrue(droneDelivery2.getDeliveryDistance() == calCulateEuclidianDistance(3, 4));
    }

}
