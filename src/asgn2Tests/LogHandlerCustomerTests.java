package asgn2Tests;


import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Restaurant.LogHandler;

/**
 * A class that tests the methods relating to the creation of Customer objects
 * in the asgn2Restaurant.LogHander class.
 *
 * @author Person A
 */
public class LogHandlerCustomerTests {

    private static final String FILE_PATH = "./testFiles";
    private LogHandler log;

    @Test
    @Before
    public void testLoadFile() {
        log = new LogHandler();
    }

    @Test(expected = LogHandlerException.class)
    public void openFile() throws CustomerException, LogHandlerException {
        ArrayList<Customer> customerSet = log.populateCustomerDataset(FILE_PATH + "test1.txt");
        assertTrue(customerSet.get(0) instanceof Customer);
        assertTrue(customerSet.get(1) instanceof Customer);
        assertTrue(customerSet.get(2) instanceof Customer);
    }
}
