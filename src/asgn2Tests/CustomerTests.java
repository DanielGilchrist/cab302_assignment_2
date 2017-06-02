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
 * @author Mustafa Hussaini
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
        pickup2 = CustomerFactory.getCustomer("PUC", "John Smith 2nd", "0123456788", 0, 0);
        droneDelivery2 = CustomerFactory.getCustomer("DNC", "David Smith 2nd", "0123455550", 3, 4);
        driverDelivery2 = CustomerFactory.getCustomer("DVC", "Tom Smith 2nd", "0123456660", 2, 9);
    }

    private static double calCulateEuclidianDistance(int x, int y) {
        return Math.sqrt(((double) x * (double) x) + ((double) y * (double) y));
    }

    // -------------------------- Test the objects ---------------------------//
    @Test
    public void testCustomerTypePickup() {
        assertTrue(pickup instanceof PickUpCustomer);
        assertTrue(pickup2 instanceof PickUpCustomer);
    }

    @Test
    public void testCustomerTypeDroneDelivery() {
        assertTrue(droneDelivery instanceof DroneDeliveryCustomer);
        assertTrue(droneDelivery2 instanceof DroneDeliveryCustomer);
    }

    @Test
    public void testCustomerTypeDriverDelivery() {
        assertTrue(driverDelivery instanceof DriverDeliveryCustomer);
        assertTrue(driverDelivery2 instanceof DriverDeliveryCustomer);
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

    // ------------------------ test customer name ------------------------- //
    @Test
    public void testGetCustomerNamePickup() {
        assertTrue("John Smith".equals(pickup.getName()));
        assertTrue("John Smith 2nd".equals(pickup2.getName()));
    }

    @Test
    public void testGetCustomerNameDroneDelivery() {
        assertTrue("David Smith".equals(droneDelivery.getName()));
        assertTrue("David Smith 2nd".equals(droneDelivery2.getName()));
    }

    @Test
    public void testGetCustomerNameDriverDelivery() {
        assertTrue("Tom Smith".equals(driverDelivery.getName()));
        assertTrue("Tom Smith 2nd".equals(driverDelivery2.getName()));
    }

    // ----------------- test customer mobile number ----------------------- //
    @Test
    public void testGetMobileNumberPickup() {
        assertTrue("0123456789".equals(pickup.getMobileNumber()));
        assertTrue("0123456788".equals(pickup2.getMobileNumber()));
    }

    @Test
    public void testGetMobileNumberDroneDelivery() {
        assertTrue("0123455555".equals(droneDelivery.getMobileNumber()));
        assertTrue("0123455550".equals(droneDelivery2.getMobileNumber()));
    }

    @Test
    public void testGetMobileNumberDriverDelivery() {
        assertTrue("0123456666".equals(driverDelivery.getMobileNumber()));
        assertTrue("0123456660".equals(driverDelivery2.getMobileNumber()));
    }

    // ------------------------ test customer type ------------------------- //
    @Test
    public void testCustomerTypes() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John Smith", "0123456789", 0, 0);
        Customer droneDelivery = CustomerFactory.getCustomer("DNC", "David Smith", "0123455555", 5, 5);
        Customer driverDelivery = CustomerFactory.getCustomer("DVC", "Tom Smith", "0123456666", 5, 5);

        assertTrue("Pick Up".equals(pickup.getCustomerType()));
        assertTrue("Drone Delivery".equals(droneDelivery.getCustomerType()));
        assertTrue("Driver Delivery".equals(driverDelivery.getCustomerType()));
    }

    // -------------------- test customer location x ----------------------- //
    @Test
    public void testPickupCutomerLocationX() {
        assertEquals(0, pickup.getLocationX());
        assertEquals(0, pickup2.getLocationX());
    }

    @Test
    public void testDroneDeliveryCutomerLocationX() {
        assertEquals(5, droneDelivery.getLocationX());
        assertEquals(3, droneDelivery2.getLocationX());
    }

    @Test
    public void testDriverDeliveryCutomerLocationX() {
        assertEquals(5, driverDelivery.getLocationX());
        assertEquals(2, driverDelivery2.getLocationX());
    }

    // -------------------- test customer location y ----------------------- //
    @Test
    public void testPickupCutomerLocationY() {
        assertEquals(0, pickup.getLocationY());
        assertEquals(0, pickup2.getLocationY());
    }

    @Test
    public void testDroneDeliveryCutomerLocationY() {
        assertEquals(5, droneDelivery.getLocationY());
        assertEquals(4, droneDelivery2.getLocationY());
    }

    @Test
    public void testDriverDeliveryCutomerLocationY() {
        assertEquals(5, driverDelivery.getLocationY());
        assertEquals(9, driverDelivery2.getLocationY());
    }

}
