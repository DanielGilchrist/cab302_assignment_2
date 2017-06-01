package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.PizzaTopping;
import asgn2Pizzas.VegetarianPizza;

/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author Person B
 *
 */
@SuppressWarnings("unused")
public class PizzaTests {
	private static final int QUANTITY = 3;
	private static final LocalTime ORDER_TIME = LocalTime.of(20, 0);
	private static final LocalTime DELIVERY_TIME = LocalTime.of(20, 15);
	private static final double MEATLOVERS_PRICE = 12.0;
	private MeatLoversPizza meatlovers;
	private VegetarianPizza vegetarian;
	private MargheritaPizza margherita;
	private double meatLoversCost;
	private final ArrayList<PizzaTopping> meatLoversToppings = new ArrayList<PizzaTopping>(Arrays.asList(
		PizzaTopping.TOMATO,
		PizzaTopping.CHEESE,
		PizzaTopping.BACON,
		PizzaTopping.PEPPERONI,
		PizzaTopping.SALAMI
	));
	
	// tests that all extended pizza classes initialise properly
	@Before
	@Test
	public void testPizzaInit() throws PizzaException {
		meatlovers = new MeatLoversPizza(QUANTITY, ORDER_TIME, DELIVERY_TIME);
		vegetarian = new VegetarianPizza(QUANTITY, ORDER_TIME, DELIVERY_TIME);
		margherita = new MargheritaPizza(QUANTITY, ORDER_TIME, DELIVERY_TIME);
		
		for(PizzaTopping topping : meatLoversToppings) {
			meatLoversCost += topping.getCost();
		}
	}
	
	// ---------------------------- Pizza Tests ------------------------------//
	
	// tests calculateCostPerPizza() and getCostPerPizza() normally
	@Test
	public void testCalculateCostPerPizza() {
		meatlovers.calculateCostPerPizza();
		assertEquals(meatLoversCost, meatlovers.getCostPerPizza(), 0);
	}
	
	// tests getCostPerPizza() for when the cost hasn't been calculated yet
	@Test
	public void testGetCostPerPizzaNotCalculated() {
		assertEquals(0.0, meatlovers.getCostPerPizza(), 0);
	}
	
	@Test
	public void testGetPricePerPizza() {
		assertEquals(MEATLOVERS_PRICE, meatlovers.getPricePerPizza(), 0);
	}
	
	@Test
	public void testGetOrderCost() {
		meatlovers.calculateCostPerPizza();
		assertEquals(meatLoversCost * QUANTITY, meatlovers.getOrderCost(), 0);
	}
	
	// test getOrderCost() for when the cost hasn't been calculated yet
	@Test
	public void testGetOrderCostNotCalculated() {
		assertEquals(0.0, meatlovers.getOrderCost(), 0);
	}
	
	@Test
	public void testGetOrderPrice() {
		assertEquals(MEATLOVERS_PRICE * QUANTITY, meatlovers.getOrderPrice(), 0);
	}
	
	@Test
	public void testGetOrderProfit() {
		meatlovers.calculateCostPerPizza();
		assertEquals(MEATLOVERS_PRICE * QUANTITY - meatLoversCost * QUANTITY, 
				meatlovers.getOrderProfit(), 0);
	}
	
	// tests getOrderProfit before the cost has been calculated
	@Test 
	public void testGetOrderProfitNotCalculated() {
		assertEquals(MEATLOVERS_PRICE * QUANTITY, meatlovers.getOrderProfit(), 0);
	}
	
	// tests containsTopping() with a topping that is on meatlovers pizza
	@Test
	public void testContainsToppingToppingExists() {
		assertTrue(meatlovers.containsTopping(meatLoversToppings.get(0)));
	}
	
	// tests containsTopping() with a topping that isn't on meatlovers pizza
	@Test
	public void testContainsToppingToppingDoesntExist() {
		assertFalse(meatlovers.containsTopping(PizzaTopping.EGGPLANT));
	}
	
	@Test
	public void testGetQuantity() {
		assertEquals(QUANTITY, meatlovers.getQuantity());
	}
	
	@Test
	public void testGetPizzaType() {
		assertEquals("Meat Lovers", meatlovers.getPizzaType());
	}
}
