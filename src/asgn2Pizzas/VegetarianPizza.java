package asgn2Pizzas;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import asgn2Exceptions.PizzaException;

/**
 * 
 * A class that represents a vegetarian pizza made at the Pizza Palace
 * restaurant. The vegetarian pizza has certain toppings listed in Section 5.1
 * of the Assignment Specification Document. A description of the class's fields
 * and their constraints is provided in Section 5.1 of the Assignment
 * Specification.
 * 
 * @author Mustafa Hussaini
 *
 */
public class VegetarianPizza extends Pizza {

    private static final double VEGETARIAN_PRICE = 10.0;

    private final ArrayList<PizzaTopping> pizzaTopping = new ArrayList<PizzaTopping>(Arrays.asList(PizzaTopping.TOMATO,
            PizzaTopping.CHEESE, PizzaTopping.EGGPLANT, PizzaTopping.MUSHROOM, PizzaTopping.CAPSICUM));

    /**
     * 
     * This class represents a vegetarian pizza made at the Pizza Palace
     * restaurant. The vegetarian pizza has certain toppings listed in Section
     * 5.1 of the Assignment Specification Document. A description of the
     * class's fields and their constraints is provided in Section 5.1 of the
     * Assignment Specification. A PizzaException is thrown if the any of the
     * constraints listed in Section 5.1 of the Assignment Specification are
     * violated.
     * 
     * <P>
     * PRE: TRUE
     * <P>
     * POST: All field values including the cost per pizza are set
     * 
     * @param quantity
     *            - The number of pizzas ordered
     * @param orderTime
     *            - The time that the pizza order was made and sent to the
     *            kitchen
     * @param deliveryTime
     *            - The time that the pizza was delivered to the customer
     * @throws PizzaException
     *             if quantity is 0, negative or greater than 10, and/or if
     *             orderTime is before 7pm, or after 11pm, and/ orif delivery
     *             time is before the order time, or before the opening time,
     *             and/or if delivery time is less than 10 minutes after the
     *             order time, and/or if delivery time is greater than 1 hour
     *             after the order time
     *
     */
    public VegetarianPizza(int quantity, LocalTime orderTime, LocalTime deliveryTime) throws PizzaException {
        super(quantity, orderTime, deliveryTime, "Vegetarian", VEGETARIAN_PRICE);
        super.pizzaTopping = this.pizzaTopping;
    }

}
