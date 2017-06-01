package asgn2Tests;

import java.time.LocalTime;
import java.time.temporal.TemporalUnit;

import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;
import asgn2Pizzas.VegetarianPizza;

/** 
 * A class that tests the asgn2Pizzas.PizzaFactory class.
 * 
 * @author Person B 
 * 
 */
@SuppressWarnings("unused")
public class PizzaFactoryTests {
	private static final int QUANTITY = 3;
	private static final LocalTime ORDER_TIME = LocalTime.of(20, 0);
	private static final LocalTime DELIVERY_TIME = LocalTime.of(20, 15);
	private static final double MEATLOVERS_PRICE = 12.0;
	private static final double VEGETARIAN_PRICE = 10.0;
	private static final double MARGHERITA_PRICE = 8.0;
	private static String[] pizzaCodes = {"PZL", "PZV", "PZM"};
	
	// tests PizzaFactory for all pizzas with valid parameters
	@Test
	public void testPizzaFactoryLegalValues() throws PizzaException {
		Pizza meatlovers = PizzaFactory.getPizza(pizzaCodes[0], QUANTITY, ORDER_TIME, DELIVERY_TIME);
		Pizza vegetarian = PizzaFactory.getPizza(pizzaCodes[1], QUANTITY, ORDER_TIME, DELIVERY_TIME);
		Pizza margherita = PizzaFactory.getPizza(pizzaCodes[2], QUANTITY, ORDER_TIME, DELIVERY_TIME);
	}
	
	@Test (expected = PizzaException.class)
	public void testPizzaFactoryInvalidPizzaCode() throws PizzaException {
		Pizza meatlovers = PizzaFactory.getPizza("", QUANTITY, ORDER_TIME, DELIVERY_TIME);
	}
	
	@Test (expected = PizzaException.class)
	public void testPizzaFactoryQuantityTooLow() throws PizzaException {
		Pizza meatlovers = PizzaFactory.getPizza(pizzaCodes[0], 0, ORDER_TIME, DELIVERY_TIME);
	}
	
	@Test (expected = PizzaException.class)
	public void testPizzaFactoryQuantityTooHigh() throws PizzaException {
		Pizza meatlovers = PizzaFactory.getPizza(pizzaCodes[0], 11, ORDER_TIME, DELIVERY_TIME);
	}
	
	@Test (expected = PizzaException.class)
	public void testPizzaFactoryOrderTooEarly() throws PizzaException {
		Pizza meatlovers = PizzaFactory.getPizza(pizzaCodes[0], QUANTITY, LocalTime.of(18, 0), DELIVERY_TIME);
	}
	
	@Test
	public void testPizzaFactoryOrderOnOpeningTime() throws PizzaException {
		LocalTime orderTime = LocalTime.of(19, 0);
		Pizza meatlovers = PizzaFactory.getPizza(pizzaCodes[0], QUANTITY, orderTime, orderTime.plusMinutes(20));
	}
	
	@Test
	public void testPizzaFactoryOrderOnCloseTime() throws PizzaException {
		LocalTime orderTime = LocalTime.of(23, 0);
		Pizza meatlovers = PizzaFactory.getPizza(pizzaCodes[0], QUANTITY, orderTime, orderTime.plusMinutes(20));
	}
	
	@Test (expected = PizzaException.class)
	public void testPizzaFactoryDeliveryTooEarly() throws PizzaException {
		Pizza meatlovers = PizzaFactory.getPizza(pizzaCodes[0], QUANTITY, ORDER_TIME, ORDER_TIME.plusMinutes(1));
	}
	
	@Test
	public void testPizzaFactoryDeliveryShortlyAfterClose() throws PizzaException {
		LocalTime orderTime = LocalTime.of(23, 00);
		Pizza meatlovers = PizzaFactory.getPizza(pizzaCodes[0], QUANTITY, orderTime, orderTime.plusMinutes(15));
	}
	
	@Test 
	public void testPizzaFactoryDeliveryExactlyOneHourAfterOrder() throws PizzaException {
		Pizza meatlovers = PizzaFactory.getPizza(pizzaCodes[0], QUANTITY, ORDER_TIME, ORDER_TIME.plusHours(1));
	}
	
	@Test (expected = PizzaException.class)
	public void testPizzaFactoryDeliveryOverOneHourAfterOrder() throws PizzaException {
		Pizza meatlovers = PizzaFactory.getPizza(pizzaCodes[0], QUANTITY, ORDER_TIME, ORDER_TIME.plusHours(2));
	}
}
