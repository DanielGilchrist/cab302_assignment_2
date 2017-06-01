package asgn2Tests;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;

/**
 * A class that tests the methods relating to the creation of Customer objects in the asgn2Restaurant.LogHander class.
 *
 * @author Person A
 */
public class LogHandlerCustomerTests {
	Customer validCustomer;
	Pizza validPizza;
	private static final String VALID_CSV = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
	
	@Before
	public void initObjects() throws CustomerException, PizzaException {
		validCustomer = CustomerFactory.getCustomer("DVC", "Casey Jones", "0123456789", 5, 5);
		validPizza = PizzaFactory.getPizza("PZV", 2, LocalTime.of(19, 0), LocalTime.of(19, 20));
	}
	
	@Test 
	public void createCustomerValidInput() {
		
	}
}
