package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that that tests the methods relating to the handling of Customer objects in the asgn2Restaurant.PizzaRestaurant
 * class as well as processLog and resetDetails.
 * 
 * @author Mustafa Hussaini
 * 
 */

public class RestaurantCustomerTests {
	private static final String FILE_PATH = "./testFiles/";
	private static final String VALID_CSV_NAME = "test_valid_order.txt";
	private PizzaRestaurant pr;
	private Customer customer1;
	private Customer customer2;
	private Customer customer3;
	
	@Before
	public void initObjects() throws CustomerException {
		pr = new PizzaRestaurant();
		
		// using data from "test_valid_order.txt"
		customer1 = CustomerFactory.getCustomer("DVC", "Casey Jones", "0123456789", 5, 5);
		customer2 = CustomerFactory.getCustomer("DNC", "April O' Neal", "0987654321", 3, 4);
		customer3 = CustomerFactory.getCustomer("PUC", "Oroku Saki", "0111222333", 0, 0);
	}
	
	@Test (expected = CustomerException.class)
	public void testProcessLogInvalidCustomerCode() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog(FILE_PATH + "test_invalid_cust_code.txt");
	}
	
	@Test (expected = CustomerException.class)
	public void testProcessLogInvalidName() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog(FILE_PATH + "test_invalid_name.txt");
	}
	
	@Test (expected = CustomerException.class)
	public void testProcessLogInvalidMobileNumber() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog(FILE_PATH + "test_invalid_mobile_number.txt");
	}
	
	@Test (expected = CustomerException.class)
	public void testProcessLogInvalidLocationX() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog(FILE_PATH + "test_invalid_location_x.txt");
	}
	
	@Test (expected = CustomerException.class)
	public void testProcessLogInvalidLocationY() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog(FILE_PATH + "test_invalid_location_y.txt");
	}
	
	@Test (expected = CustomerException.class)
	public void testProcessLogBlankCustomerInformation() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog(FILE_PATH + "test_blank_cust_info.txt");
	}
	
	// doesn't matter which test file the next two tests are in as they aren't
	// particularly relevant to either customers or pizza
	@Test (expected = LogHandlerException.class)
	public void testProcessLogNotCSVFormat() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog(FILE_PATH + "test_not_CSV.txt");
	}
	
	@Test (expected = LogHandlerException.class)
	public void testProcessLogEmptyFile() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog(FILE_PATH + "test_empty.txt");
	}
	
	@Test (expected = LogHandlerException.class)
	public void testProcessLogMissingCustomerInformation() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog(FILE_PATH + "test_missing_cust_info.txt");
	}
	
	@Test
	public void testGetCustomerByIndex() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog(FILE_PATH + VALID_CSV_NAME);
		assertEquals(customer1, pr.getCustomerByIndex(0));
	}
	
	@Test
	(expected = CustomerException.class)
	public void testGetCustomerByIndexNoCustomers() throws CustomerException {
		pr.getCustomerByIndex(0);
	}
	
	// same a above test except there are registered customers
	@Test
	(expected = CustomerException.class)
	public void testGetCustomerByIndexIndexTooHigh() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog(FILE_PATH + VALID_CSV_NAME);
		pr.getCustomerByIndex(4);
	}
	
	@Test
	public void testGetNumCustomerOrdersNoCustomers() {
		assertEquals(0, pr.getNumCustomerOrders());
	}
	
	@Test
	public void testGetNumCustomerOrders() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog(FILE_PATH + VALID_CSV_NAME);
		assertEquals(3, pr.getNumCustomerOrders());
	}
	
	@Test
	public void testGetTotalDeliveryDistanceNoCustomers() {
		assertEquals(0.0, pr.getTotalDeliveryDistance(), 0);
	}
	
	@Test
	public void testGetTotalDeliveryDistance() throws CustomerException, PizzaException, LogHandlerException {
		double expectedDist = customer1.getDeliveryDistance() + 
							  customer2.getDeliveryDistance() + 
							  customer3.getDeliveryDistance();
		
		pr.processLog(FILE_PATH + VALID_CSV_NAME);
		
		assertEquals(expectedDist, pr.getTotalDeliveryDistance(), 0);
	}
	
	@Test
	public void testResetDetailsCustomersReset() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog(FILE_PATH + VALID_CSV_NAME);
		assertEquals(3, pr.getNumCustomerOrders());
		pr.resetDetails();
		assertEquals(0, pr.getNumCustomerOrders());
	}
}
