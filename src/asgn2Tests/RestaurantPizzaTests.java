package asgn2Tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that tests the methods relating to the handling of Pizza objects in the asgn2Restaurant.PizzaRestaurant class as well as
 * processLog and resetDetails.
 * 
 * @author Person B
 *
 */
public class RestaurantPizzaTests {
	private static final String VALID_CSV_NAME = "test_valid_order.txt";
	private PizzaRestaurant pr;
	private Pizza pizza1;
	private Pizza pizza2;
	private Pizza pizza3;
	
	@Before
	public void initObjects() throws PizzaException {
		pr = new PizzaRestaurant();
		
		// using data from "test_valid_order.txt"
		pizza1 = PizzaFactory.getPizza("PZV", 2, LocalTime.of(19, 0), LocalTime.of(19, 20));
		pizza2 = PizzaFactory.getPizza("PZM", 1, LocalTime.of(20, 0), LocalTime.of(20, 25));
		pizza3 = PizzaFactory.getPizza("PZL", 3, LocalTime.of(21, 0), LocalTime.of(21, 35));
	}
	
	@Test
	(expected = PizzaException.class)
	public void testProcessLogInvalidPizzaCode() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog("test_invalid_pizza_code.txt");
	}
	
	@Test
	(expected = PizzaException.class)
	public void testProcessLogInvalidQuantity() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog("test_invalid_quantity.txt");
	}
	
	@Test
	(expected = PizzaException.class)
	public void testProcessLogInvalidOrderTime() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog("test_invalid_order_time.txt");
	}
	
	@Test
	(expected = PizzaException.class)
	public void testProcessLogInvalidDeliveryTime() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog("test_invalid_delivery_time.txt");
	}
	
	@Test
	(expected = PizzaException.class)
	public void testProcessLogBlankPizzaInformation() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog("test_blank_pizza_info.txt");
	}
	
	@Test
	(expected = LogHandlerException.class)
	public void testProcessLogMissingPizzaInformation() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog("test_missing_pizza_info.txt");
	}
	
	@Test
	public void testGetPizzaByIndex() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog(VALID_CSV_NAME);
		assertEquals(pizza1, pr.getPizzaByIndex(0));
	}
	
	@Test
	(expected = PizzaException.class)
	public void testGetPizzaByIndexNoOrders() throws PizzaException {
		pr.getPizzaByIndex(0);
	}
	
	// same a above test except there are registered customers
	@Test
	(expected = PizzaException.class)
	public void testGetPizzaByIndexIndexTooHigh() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog(VALID_CSV_NAME);
		pr.getPizzaByIndex(4);
	}
	
	@Test
	public void testGetNumPizzaOrdersNoOrders() {
		assertEquals(0, pr.getNumPizzaOrders());
	}
	
	@Test
	public void testGetNumPizzaOrders() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog(VALID_CSV_NAME);
		assertEquals(3, pr.getNumPizzaOrders());
	}
	
	@Test
	public void testGetTotalProfitNoOrders() {
		assertEquals(0, pr.getTotalProfit(), 0);
	}
	
	@Test
	public void testGetTotalProfit() throws CustomerException, PizzaException, LogHandlerException {
		pizza1.calculateCostPerPizza();
		pizza2.calculateCostPerPizza();
		pizza3.calculateCostPerPizza();
		
		double expectedProfit = pizza1.getOrderProfit() + 
								pizza2.getOrderProfit() +
								pizza3.getOrderProfit();
		
		pr.processLog(VALID_CSV_NAME);
		assertEquals(expectedProfit, pr.getTotalProfit(), 0);
	}
	
	@Test
	public void testResetDetailsPizzaReset() throws CustomerException, PizzaException, LogHandlerException {
		pr.processLog(VALID_CSV_NAME);
		assertEquals(3, pr.getNumPizzaOrders());
		pr.resetDetails();
		assertEquals(0, pr.getNumPizzaOrders());
	}
}
