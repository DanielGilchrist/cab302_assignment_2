package asgn2Restaurant;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;

/**
 *
 * A class that contains methods that use the information in the log file to
 * return Pizza and Customer object - either as an individual Pizza/Customer
 * object or as an ArrayList of Pizza/Customer objects.
 * 
 * @author Mustafa Hussaini and Daniel Gilchrist
 *
 */
public class LogHandler {
    private static final int NUM_VALID_INPUT = 9;

    /**
     * Returns an ArrayList of Customer objects from the information contained
     * in the log file ordered as they appear in the log file.
     * 
     * @param filename
     *            The file name of the log file
     * @return an ArrayList of Customer objects from the information contained
     *         in the log file ordered as they appear in the log file.
     * @throws CustomerException
     *             if the name is empty, white space, or longer than 20
     *             characters, and/or if mobile number is not 10 digits starting
     *             by 0, and/or locationX and locationY are less than -10 or
     *             greater than 10
     * @throws LogHandlerException
     *             If there was a problem with the log file not related to the
     *             semantic errors above
     * 
     */
    public static ArrayList<Customer> populateCustomerDataset(String filename)
            throws CustomerException, LogHandlerException {
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            if (fileIsEmpty(filename)) {
                throw new LogHandlerException("File is empty");
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                customers.add(createCustomer(line));
            }

            bufferedReader.close();
        } catch (IOException e) {
            customers.clear();
            throw new LogHandlerException(e.toString());
        } catch (CustomerException e) {
            customers.clear();
            throw new CustomerException(e.toString());
        }

        return customers;
    }

    /**
     * Returns an ArrayList of Pizza objects from the information contained in
     * the log file ordered as they appear in the log file. .
     * 
     * @param filename
     *            The file name of the log file
     * @return an ArrayList of Pizza objects from the information contained in
     *         the log file ordered as they appear in the log file. .
     * @throws PizzaException
     *             if quantity is 0, negative or greater than 10, and/or if
     *             orderTime is before 7pm, or after 11pm, and/ orif delivery
     *             time is before the order time, or before the opening time,
     *             and/or if delivery time is less than 10 minutes after the
     *             order time, and/or if delivery time is greater than 1 hour
     *             after the order time
     * @throws LogHandlerException
     *             If there was a problem with the log file not related to the
     *             semantic errors above
     * 
     */
    public static ArrayList<Pizza> populatePizzaDataset(String filename) throws PizzaException, LogHandlerException {
        ArrayList<Pizza> pizzas = new ArrayList<>();

        try {
            if (fileIsEmpty(filename)) {
                throw new LogHandlerException("File is empty");
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                pizzas.add(createPizza(line));
            }

            bufferedReader.close();
        } catch (IOException e) {
            pizzas.clear();
            throw new LogHandlerException(e.toString());
        } catch (PizzaException e) {
            pizzas.clear();
            throw new PizzaException(e.toString());
        }

        return pizzas;
    }

    private static boolean fileIsEmpty(String filename) throws IOException {
        boolean isEmpty;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

        if (bufferedReader.readLine() == null) {
            isEmpty = true;
        } else {
            isEmpty = false;
        }

        bufferedReader.close();

        return isEmpty;
    }

    /**
     * Creates a Customer object by parsing the information contained in a
     * single line of the log file. The format of each line is outlined in
     * Section 5.3 of the Assignment Specification.
     * 
     * @param line
     *            - A line from the log file
     * @return- A Customer object containing the information from the line in
     *          the log file
     * @throws CustomerException
     *             if the name is empty, white space, or longer than 20
     *             characters, and/or if mobile number is not 10 digits starting
     *             by 0, and/or locationX and locationY are less than -10 or
     *             greater than 10
     * @throws LogHandlerException
     *             - If there was a problem parsing the line from the log file.
     */
    public static Customer createCustomer(String line) throws CustomerException, LogHandlerException {
        Customer customer;
        String[] words = line.split(",");

        if (words.length != NUM_VALID_INPUT) {
            throw new LogHandlerException("There was a problem parsing the line from the log file.");
        }

        try {
            customer = CustomerFactory.getCustomer(words[4], words[2], words[3], Integer.parseInt(words[5]),
                    Integer.parseInt(words[6]));
        } catch (Exception e) {
            throw new CustomerException(e.toString());
        }

        return customer;
    }

    /**
     * Creates a Pizza object by parsing the information contained in a single
     * line of the log file. The format of each line is outlined in Section 5.3
     * of the Assignment Specification.
     * 
     * @param line
     *            - A line from the log file
     * @return- A Pizza object containing the information from the line in the
     *          log file
     * @throws PizzaException
     *             if quantity is 0, negative or greater than 10, and/or if
     *             orderTime is before 7pm, or after 11pm, and/ orif delivery
     *             time is before the order time, or before the opening time,
     *             and/or if delivery time is less than 10 minutes after the
     *             order time, and/or if delivery time is greater than 1 hour
     *             after the order time
     * @throws LogHandlerException
     *             - If there was a problem parsing the line from the log file.
     */
    public static Pizza createPizza(String line) throws PizzaException, LogHandlerException {
        Pizza pizza;
        String[] words = line.split(",");

        if (words.length != NUM_VALID_INPUT) {
            throw new LogHandlerException("There was a problem parsing the line from the log file.");
        }

        try {
            pizza = PizzaFactory.getPizza(words[7], Integer.parseInt(words[8]), LocalTime.parse(words[0]),
                    LocalTime.parse(words[1]));
        } catch (Exception e) {
            throw new PizzaException(e.toString());
        }

        return pizza;
    }

}
