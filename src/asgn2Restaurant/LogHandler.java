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
 * @author Mustafa Hussaini and Person B
 *
 */
public class LogHandler {

    /**
     * Returns an ArrayList of Customer objects from the information contained
     * in the log file ordered as they appear in the log file.
     * 
     * @param filename
     *            The file name of the log file
     * @return an ArrayList of Customer objects from the information contained
     *         in the log file ordered as they appear in the log file.
     * @throws CustomerException
     *             If the log file contains semantic errors leading that violate
     *             the customer constraints listed in Section 5.3 of the
     *             Assignment Specification or contain an invalid customer code
     *             (passed by another class).
     * @throws LogHandlerException
     *             If there was a problem with the log file not related to the
     *             semantic errors above
     * 
     */
    public static ArrayList<Customer> populateCustomerDataset(String filename) 
    		throws CustomerException, LogHandlerException {
    	
    	ArrayList<Customer> customers = new ArrayList<>();
        
        try {
        	BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String line = null;
            
            while ((line = bufferedReader.readLine()) != null) {
            	customers.add(createCustomer(line));
            }
            
            bufferedReader.close();
        } catch (IOException e) {
        	throw new LogHandlerException(e.toString());
        } catch (CustomerException e) {
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
     *             If the log file contains semantic errors leading that violate
     *             the pizza constraints listed in Section 5.3 of the Assignment
     *             Specification or contain an invalid pizza code (passed by
     *             another class).
     * @throws LogHandlerException
     *             If there was a problem with the log file not related to the
     *             semantic errors above
     * 
     */
    public static ArrayList<Pizza> populatePizzaDataset(String filename) 
    		throws PizzaException, LogHandlerException {
    	
    	ArrayList<Pizza> pizzas = new ArrayList<>();
        
        try {
        	BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String line = null;
            
            while ((line = bufferedReader.readLine()) != null) {
            	pizzas.add(createPizza(line));
            }
            
    		bufferedReader.close();
        } catch (IOException e) {
        	throw new LogHandlerException(e.toString());
        } catch (PizzaException e) {
        	throw new PizzaException(e.toString());
        }
        
        return pizzas;
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
     *             - If the log file contains semantic errors leading that
     *             violate the customer constraints listed in Section 5.3 of the
     *             Assignment Specification or contain an invalid customer code
     *             (passed by another class).
     * @throws LogHandlerException
     *             - If there was a problem parsing the line from the log file.
     */
    public static Customer createCustomer(String line) throws CustomerException, LogHandlerException {
        String[] words = line.split(",");
        return CustomerFactory.getCustomer(words[4], words[2], words[3], 
        		Integer.parseInt(words[5]), Integer.parseInt(words[6]));
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
     *             If the log file contains semantic errors leading that violate
     *             the pizza constraints listed in Section 5.3 of the Assignment
     *             Specification or contain an invalid pizza code (passed by
     *             another class).
     * @throws LogHandlerException
     *             - If there was a problem parsing the line from the log file.
     */
    public static Pizza createPizza(String line) throws PizzaException, LogHandlerException {
        String[] words = line.split(",");
        return PizzaFactory.getPizza(words[7], Integer.parseInt(words[8]), 
				LocalTime.parse(words[0]), LocalTime.parse(words[1]));
    }

}
