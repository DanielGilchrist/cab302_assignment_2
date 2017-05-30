package asgn2Tests;


import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
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

    @Test
    @Before
    public void getInstances() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John Smith", "0123456789", 0, 0);
        Customer droneDelivery = CustomerFactory.getCustomer("DNC", "David Smith", "0123455555", 5, 5);
        Customer driverDelivery = CustomerFactory.getCustomer("DVC", "Tom Smith", "0123456666", 5, 5);
    }
}
