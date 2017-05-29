package asgn2Tests;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;
import asgn2Exceptions.CustomerException;

/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 * 
 * @author Person A
 *
 */
public class CustomerFactoryTests {
    // private vairables
    Customer pickup;
    Customer droneDelivery;
    Customer driverDelivery;

    @Test
    @Before
    public void testTheFactory() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John Smith", "0123456789", 0, 0);
        Customer droneDelivery = CustomerFactory.getCustomer("DNC", "David Smith", "0123455555", 5, 5);
        Customer driverDelivery = CustomerFactory.getCustomer("DVC", "Tom Smith", "0123456666", 5, 5);
    }

    // -------------------------- Test the objects ---------------------------//
    @Test
    public void testCustomerTypePickup() {
        assertEquals(true, pickup instanceof PickUpCustomer);
    }

    @Test
    public void testCustomerTypeDroneDelivery() {
        assertEquals(true, droneDelivery instanceof DroneDeliveryCustomer);
    }

    @Test
    public void testCustomerTypeDriverDelivery() {
        assertEquals(true, driverDelivery instanceof DriverDeliveryCustomer);
    }

    // --------------------------- No Type parameter -------------------------//
    @Test(expected = CustomerException.class)
    public void testNullType1() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("", "John Smith", "0123456789", 0, 0);
    }

    @Test(expected = CustomerException.class)
    public void testNullType2() throws CustomerException {
        Customer droneDelivery = CustomerFactory.getCustomer("", "David Smith", "0123455555", 5, 5);
    }

    @Test(expected = CustomerException.class)
    public void testNullType3() throws CustomerException {
        Customer driverDelivery = CustomerFactory.getCustomer("", "Tom Smith", "0123456666", 5, 5);
    }

    // --------------------------- No name parameter -------------------------//
    @Test(expected = CustomerException.class)
    public void testEmptyName1() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "", "0123456789", 0, 0);
    }

    @Test(expected = CustomerException.class)
    public void testEmptyName2() throws CustomerException {
        Customer droneDelivery = CustomerFactory.getCustomer("DNC", "", "0123455555", 5, 5);
    }

    @Test(expected = CustomerException.class)
    public void testEmptyName3() throws CustomerException {
        Customer driverDelivery = CustomerFactory.getCustomer("DVC", "", "0123456666", 5, 5);
    }

    // --------------------- White space name parameter ----------------------//
    @Test(expected = CustomerException.class)
    public void testWhiteSpaceName1() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "         ", "0123456789", 0, 0);
    }

    @Test(expected = CustomerException.class)
    public void testWhiteSpaceName2() throws CustomerException {
        Customer droneDelivery = CustomerFactory.getCustomer("DNC", "                   ", "0123455555", 5, 5);
    }

    @Test(expected = CustomerException.class)
    public void testWhiteSpaceName3() throws CustomerException {
        Customer driverDelivery = CustomerFactory.getCustomer("DVC", "                ", "0123456666", 5, 5);
    }

    // ----------------------------- Long name -------------------------------//
    @Test(expected = CustomerException.class)
    public void testLongName1() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John David Smith Jr.", "0123456789", 0, 0);
    }

    @Test(expected = CustomerException.class)
    public void testLongName2() throws CustomerException {
        Customer droneDelivery = CustomerFactory.getCustomer("DNC", "John David Smith Jr.", "0123455555", 5, 5);
    }

    @Test(expected = CustomerException.class)
    public void testLongName3() throws CustomerException {
        Customer driverDelivery = CustomerFactory.getCustomer("DVC", "John David Smith Jr.", "0123456666", 5, 5);
    }

    // ----------------------------- Longer name -----------------------------//
    @Test(expected = CustomerException.class)
    public void testLongName4() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John David Smith Jr. 2nd", "0123456789", 0, 0);
    }

    @Test(expected = CustomerException.class)
    public void testLongName5() throws CustomerException {
        Customer droneDelivery = CustomerFactory.getCustomer("DNC", "John David Smith Jr. 2nd", "0123455555", 5, 5);
    }

    @Test(expected = CustomerException.class)
    public void testLongName6() throws CustomerException {
        Customer driverDelivery = CustomerFactory.getCustomer("DVC", "John David Smith Jr. 2nd", "0123456666", 5, 5);
    }

    // ---------------------------- No PhoneNumber ---------------------------//
    @Test(expected = CustomerException.class)
    public void testEmptyPhoneNumber1() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John Smith", "", 0, 0);
    }

    @Test(expected = CustomerException.class)
    public void testEmptyPhoneNumber2() throws CustomerException {
        Customer droneDelivery = CustomerFactory.getCustomer("DNC", "David Smith", "", 5, 5);
    }

    @Test(expected = CustomerException.class)
    public void testEmptyPhoneNumber3() throws CustomerException {
        Customer driverDelivery = CustomerFactory.getCustomer("DVC", "Tom Smith", "", 5, 5);
    }

    // -------------------------- Short PhoneNumber --------------------------//
    @Test(expected = CustomerException.class)
    public void testShortPhoneNumber1() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John Smith", "012345678", 0, 0);
    }

    @Test(expected = CustomerException.class)
    public void testShortPhoneNumber2() throws CustomerException {
        Customer droneDelivery = CustomerFactory.getCustomer("DNC", "David Smith", "012345678", 5, 5);
    }

    @Test(expected = CustomerException.class)
    public void testShortPhoneNumber3() throws CustomerException {
        Customer driverDelivery = CustomerFactory.getCustomer("DVC", "Tom Smith", "012345678", 5, 5);
    }

    // --------------------------- Long PhoneNumber --------------------------//
    @Test(expected = CustomerException.class)
    public void testLongPhoneNumber1() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John Smith", "01234567890", 0, 0);
    }

    @Test(expected = CustomerException.class)
    public void testLongPhoneNumber2() throws CustomerException {
        Customer droneDelivery = CustomerFactory.getCustomer("DNC", "David Smith", "01234567890", 5, 5);
    }

    @Test(expected = CustomerException.class)
    public void testLongPhoneNumber3() throws CustomerException {
        Customer driverDelivery = CustomerFactory.getCustomer("DVC", "Tom Smith", "01234567890", 5, 5);
    }

    // -------------------------- Longer PhoneNumber -------------------------//
    @Test(expected = CustomerException.class)
    public void testLongerPhoneNumber1() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John Smith", "01234567890123", 0, 0);
    }

    @Test(expected = CustomerException.class)
    public void testLongerPhoneNumber2() throws CustomerException {
        Customer droneDelivery = CustomerFactory.getCustomer("DNC", "David Smith", "01234567890123", 5, 5);
    }

    @Test(expected = CustomerException.class)
    public void testLongerPhoneNumber3() throws CustomerException {
        Customer driverDelivery = CustomerFactory.getCustomer("DVC", "Tom Smith", "01234567890123", 5, 5);
    }

    // ------------------------- Non Zero PhoneNumber ------------------------//
    @Test(expected = CustomerException.class)
    public void testNonZeroPhoneNumber1() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John Smith", "1234567890", 0, 0);
    }

    @Test(expected = CustomerException.class)
    public void testNonZeroPhoneNumber2() throws CustomerException {
        Customer droneDelivery = CustomerFactory.getCustomer("DNC", "David Smith", "1234567890", 5, 5);
    }

    @Test(expected = CustomerException.class)
    public void testNonZeroPhoneNumber3() throws CustomerException {
        Customer driverDelivery = CustomerFactory.getCustomer("DVC", "Tom Smith", "1234567890", 5, 5);
    }

    // ---------------------------- Pickup Location --------------------------//
    @Test(expected = CustomerException.class)
    public void testPickUpLocation1() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John Smith", "0123456789", 10, 0);
    }

    @Test(expected = CustomerException.class)
    public void testPickUpLocation2() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John Smith", "0123456789", 0, 10);
    }

    @Test(expected = CustomerException.class)
    public void testPickUpLocation3() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John Smith", "0123456789", -10, 0);
    }

    @Test(expected = CustomerException.class)
    public void testPickUpLocation4() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John Smith", "0123456789", 0, -10);
    }

    @Test(expected = CustomerException.class)
    public void testPickUpLocation5() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John Smith", "0123456789", 11, 0);
    }

    @Test(expected = CustomerException.class)
    public void testPickUpLocation6() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John Smith", "0123456789", 0, 11);
    }

    @Test(expected = CustomerException.class)
    public void testPickUpLocation7() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John Smith", "0123456789", -11, 0);
    }

    @Test(expected = CustomerException.class)
    public void testPickUpLocation8() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John Smith", "0123456789", 0, -11);
    }

    @Test(expected = CustomerException.class)
    public void testPickUpLocation9() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John Smith", "0123456789", 1, 0);
    }

    @Test(expected = CustomerException.class)
    public void testPickUpLocation10() throws CustomerException {
        Customer pickup = CustomerFactory.getCustomer("PUC", "John Smith", "0123456789", 0, 1);
    }

    // ---------------------------- Drone Location ---------------------------//
    @Test(expected = CustomerException.class)
    public void testDroneLocation1() throws CustomerException {
        Customer droneDelivery = CustomerFactory.getCustomer("DNC", "David Smith", "0123455555", -11, 5);
    }

    @Test(expected = CustomerException.class)
    public void testDroneLocation2() throws CustomerException {
        Customer droneDelivery = CustomerFactory.getCustomer("DNC", "David Smith", "0123455555", 5, 11);
    }

    @Test
    public void testDroneLocation3() throws CustomerException {
        Customer droneDelivery = CustomerFactory.getCustomer("DNC", "David Smith", "0123455555", 10, 5);
        assertEquals(true, droneDelivery instanceof DroneDeliveryCustomer);
    }

    @Test
    public void testDroneLocation4() throws CustomerException {
        Customer droneDelivery = CustomerFactory.getCustomer("DNC", "David Smith", "0123455555", 5, 10);
        assertEquals(true, droneDelivery instanceof DroneDeliveryCustomer);
    }

    // ---------------------------- Driver Location --------------------------//
    @Test(expected = CustomerException.class)
    public void testDriveLocation1() throws CustomerException {
        Customer driverDelivery = CustomerFactory.getCustomer("DVC", "Tom Smith", "0123456666", -11, 5);
    }

    @Test(expected = CustomerException.class)
    public void testDriveLocation2() throws CustomerException {
        Customer driverDelivery = CustomerFactory.getCustomer("DVC", "Tom Smith", "0123456666", 5, -11);
    }

    @Test
    public void testDriveLocation3() throws CustomerException {
        Customer driverDelivery = CustomerFactory.getCustomer("DVC", "Tom Smith", "0123456666", 10, 5);
        assertEquals(true, driverDelivery instanceof DriverDeliveryCustomer);
    }

    @Test
    public void testDriveLocation4() throws CustomerException {
        Customer driverDelivery = CustomerFactory.getCustomer("DVC", "Tom Smith", "0123456666", 5, 10);
        assertEquals(true, driverDelivery instanceof DriverDeliveryCustomer);
    }

}
