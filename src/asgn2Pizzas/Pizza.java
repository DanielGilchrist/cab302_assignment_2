package asgn2Pizzas;


import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

import asgn2Exceptions.PizzaException;

/**
 * An abstract class that represents pizzas sold at the Pizza Palace restaurant.
 * The Pizza class is used as a base class of VegetarianPizza, MargheritaPizza
 * and MeatLoversPizza. Each of these subclasses have a different set of
 * toppings. A description of the class's fields and their constraints is
 * provided in Section 5.1 of the Assignment Specification.
 * 
 * @author Mustafa Hussaini
 *
 */
public abstract class Pizza {

    // constants and static variables
    private static final int MINIMUM_QUANTITY = 1;
    private static final int MAXIMUM_QUANTITY = 10;
    private static final LocalTime OPENING_TIME = LocalTime.of(19, 0, 0);
    private static final LocalTime CLOSING_TIME = LocalTime.of(23, 0, 0);
    private static final long ONE_HOUR = 3600000;
    private static final long TEN_MINUTES = 600000;

    // private variables
    private int quantity;
    private LocalTime orderTime;
    private LocalTime deliveryTime;
    private String type;
    private double price;
    private double cost;
    protected ArrayList<PizzaTopping> pizzaTopping;

    /**
     * This class represents a pizza produced at the Pizza Palace restaurant. A
     * detailed description of the class's fields and parameters is provided in
     * the Assignment Specification, in particular in Section 5.1. A
     * PizzaException is thrown if the any of the constraints listed in Section
     * 5.1 of the Assignment Specification are violated.
     *
     * PRE: TRUE POST: All field values except cost per pizza are set
     * 
     * @param quantity
     *            - The number of pizzas ordered
     * @param orderTime
     *            - The time that the pizza order was made and sent to the
     *            kitchen
     * @param deliveryTime
     *            - The time that the pizza was delivered to the customer
     * @param type
     *            - A human understandable description of this Pizza type
     * @param price
     *            - The price that the pizza is sold to the customer
     * @throws PizzaException
     *             if supplied parameters are invalid
     * 
     */
    public Pizza(int quantity, LocalTime orderTime, LocalTime deliveryTime, String type, double price)
            throws PizzaException {

        if (!isValidQuantity(quantity)) {
            throw new PizzaException("Quantity can only be between 1 and 10 inclusive!");
        } else if (!isValidOrderTime(orderTime)) {
            throw new PizzaException("Order time must be during working hours!");
        } else if (!isValidDeliveryTime(deliveryTime)) {
            throw new PizzaException("Orders must deliver after opening time!");
        } else if (!isValidElapsedTime(orderTime, deliveryTime)) {
            throw new PizzaException("A pizza cannot be delivered in less than 10 minutes or longer than an hour");
        }
        this.quantity = quantity;
        this.orderTime = orderTime;
        this.deliveryTime = deliveryTime;
        this.type = type;
        this.price = price;

    }

    private boolean isValidDeliveryTime(LocalTime deliveryTime) {
        return !deliveryTime.isBefore(OPENING_TIME);
    }

    private boolean isValidOrderTime(LocalTime orderTime) {
        return !(orderTime.isBefore(OPENING_TIME) && orderTime.isAfter(CLOSING_TIME));
    }

    private boolean isValidQuantity(int quantity) {
        return (quantity >= MINIMUM_QUANTITY && quantity <= MAXIMUM_QUANTITY);
    }

    private boolean isValidElapsedTime(LocalTime orderTime, LocalTime deliveryTime) {
        long elapsedTime = Duration.between(orderTime, deliveryTime).toMillis();
        return elapsedTime < ONE_HOUR && !(elapsedTime < TEN_MINUTES);
    }

    /**
     * Calculates how much a pizza would could to make calculated from its
     * toppings.
     * 
     * <P>
     * PRE: TRUE
     * <P>
     * POST: The cost field is set to sum of the Pizzas's toppings
     */
    public final void calculateCostPerPizza() {
        this.cost = calculateTopping(this.pizzaTopping);
    }

    private double calculateTopping(ArrayList<PizzaTopping> toppings) {
        double cost = 0;
        for (PizzaTopping pizzaTopping : toppings) {
            cost += pizzaTopping.getCost();
        }
        return cost;
    }

    /**
     * Returns the amount that an individual pizza costs to make.
     * 
     * @return The amount that an individual pizza costs to make.
     */
    public final double getCostPerPizza() {
        return this.cost;
    }

    /**
     * Returns the amount that an individual pizza is sold to the customer.
     * 
     * @return The amount that an individual pizza is sold to the customer.
     */
    public final double getPricePerPizza() {
        return this.price;
    }

    /**
     * Returns the amount that the entire order costs to make, taking into
     * account the type and quantity of pizzas.
     * 
     * @return The amount that the entire order costs to make, taking into
     *         account the type and quantity of pizzas.
     */
    public final double getOrderCost() {
        calculateCostPerPizza();
        return (double) quantity * cost;
    }

    /**
     * Returns the amount that the entire order is sold to the customer, taking
     * into account the type and quantity of pizzas.
     * 
     * @return The amount that the entire order is sold to the customer, taking
     *         into account the type and quantity of pizzas.
     */
    public final double getOrderPrice() {
        return (double) quantity * price;
    }

    /**
     * Returns the profit made by the restaurant on the order which is the order
     * price minus the order cost.
     * 
     * @return Returns the profit made by the restaurant on the order which is
     *         the order price minus the order cost.
     */
    public final double getOrderProfit() {
        return this.getOrderPrice() - this.getOrderCost();
    }

    /**
     * Indicates if the pizza contains the specified pizza topping or not.
     * 
     * @param topping
     *            - A topping as specified in the enumeration PizzaTopping
     * @return Returns true if the instance of Pizza contains the specified
     *         topping and false otherwise.
     */
    public final boolean containsTopping(PizzaTopping topping) {
        return this.pizzaTopping.contains(topping);
    }

    /**
     * Returns the quantity of pizzas ordered.
     * 
     * @return the quantity of pizzas ordered.
     */
    public final int getQuantity() {
        return this.quantity;
    }

    /**
     * Returns a human understandable description of the Pizza's type. The valid
     * alternatives are listed in Section 5.1 of the Assignment Specification.
     * 
     * @return A human understandable description of the Pizza's type.
     */
    public final String getPizzaType() {
        return this.type;
    }

    /**
     * Compares *this* Pizza object with an instance of an *other* Pizza object
     * and returns true if if the two objects are equivalent, that is, if the
     * values exposed by public methods are equal. You do not need to test this
     * method.
     * 
     * @return true if *this* Pizza object and the *other* Pizza object have the
     *         same values returned for getCostPerPizza(), getOrderCost(),
     *         getOrderPrice(), getOrderProfit(), getPizzaType(),
     *         getPricePerPizza() and getQuantity().
     * 
     */
    @Override
    public boolean equals(Object other) {
        Pizza otherPizza = (Pizza) other;
        return ((this.getCostPerPizza()) == (otherPizza.getCostPerPizza())
                && (this.getOrderCost()) == (otherPizza.getOrderCost()))
                && (this.getOrderPrice()) == (otherPizza.getOrderPrice())
                && (this.getOrderProfit()) == (otherPizza.getOrderProfit())
                && (this.getPizzaType() == (otherPizza.getPizzaType())
                        && (this.getPricePerPizza()) == (otherPizza.getPricePerPizza())
                        && (this.getQuantity()) == (otherPizza.getQuantity()));
    }

}
