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

    private static final String FILE_PATH = "./testFiles/";
    private LogHandler log;
    private ArrayList<Customer> customerSet;

    @Test
    @Before
    public void testLoadFile() throws CustomerException, LogHandlerException {
        log = new LogHandler();
        customerSet = log.populateCustomerDataset(FILE_PATH + "valid_test.txt");
    }

    @Test
    public void testObjectType() {
        assertTrue(customerSet.get(0) instanceof Customer);
        assertTrue(customerSet.get(1) instanceof Customer);
        assertTrue(customerSet.get(2) instanceof Customer);
    }

    @Test
    public void testCustomersNames() {
        assertTrue("Casey Jones".equals(customerSet.get(0).getName()));
        assertTrue("April O'Neal".equals(customerSet.get(1).getName()));
        assertTrue("Oroku Saki".equals(customerSet.get(2).getName()));
    }

    @Test
    public void testCustomerPhoneNumbers() {
        assertTrue("0123456789".equals(customerSet.get(0).getMobileNumber()));
        assertTrue("0987654321".equals(customerSet.get(1).getMobileNumber()));
        assertTrue("0111222333".equals(customerSet.get(2).getMobileNumber()));
    }

    @Test
    public void testCustomerType() {
        assertTrue("Driver Delivery".equals(customerSet.get(0).getCustomerType()));
        assertTrue("Drone Delivery".equals(customerSet.get(1).getCustomerType()));
        assertTrue("Pick Up".equals(customerSet.get(2).getCustomerType()));
    }

    // ------------------- Test errors in the log file ----------------------//
    @Test(expected = LogHandlerException.class)
    public void testFileNotExists() throws CustomerException, LogHandlerException {
        LogHandler localLog = new LogHandler();
        ArrayList<Customer> customers = localLog.populateCustomerDataset(FILE_PATH + "broken_File.txt");
    }

    @Test(expected = LogHandlerException.class)
    public void testFileWithExtraParameters() throws CustomerException, LogHandlerException {
        LogHandler localLog = new LogHandler();
        ArrayList<Customer> customers = localLog.populateCustomerDataset(FILE_PATH + "extra_parameter_first_line.txt");
    }

    @Test(expected = LogHandlerException.class)
    public void testFileWithExtraParametersSecondLine() throws CustomerException, LogHandlerException {
        LogHandler localLog = new LogHandler();
        ArrayList<Customer> customers = localLog.populateCustomerDataset(FILE_PATH + "extra_parameter_second_line.txt");
    }

    @Test(expected = LogHandlerException.class)
    public void testFileWithExtraParametersLastLine() throws CustomerException, LogHandlerException {
        LogHandler localLog = new LogHandler();
        ArrayList<Customer> customers = localLog.populateCustomerDataset(FILE_PATH + "extra_parameter_Last_line.txt");
        assertTrue("Driver Delivery".equals(customers.get(0).getCustomerType()));
    }

    @Test
    public void testFileWithExtraParametersLastLineIntegrity() throws CustomerException, LogHandlerException {
        LogHandler localLog = new LogHandler();
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            customers = localLog.populateCustomerDataset(FILE_PATH + "extra_parameter_Last_line.txt");
        } catch (Exception e) {
            e.getMessage();
        }
        assertTrue(customers.isEmpty());
    }

    @Test(expected = LogHandlerException.class)
    public void testFileFirstLineNoReturn() throws CustomerException, LogHandlerException {
        LogHandler localLog = new LogHandler();
        ArrayList<Customer> customers = localLog.populateCustomerDataset(FILE_PATH + "no_return_first_line.txt");
    }

    @Test(expected = LogHandlerException.class)
    public void testErrorInLastLine() throws CustomerException, LogHandlerException {
        LogHandler localLog = new LogHandler();
        ArrayList<Customer> customers = localLog.populateCustomerDataset(FILE_PATH + "last_Line_return.txt");
    }

    // ----------------------------- empty log ------------------------------//
    @Test // (expected = LogHandlerException.class)
    public void testEmptyLogFile() throws CustomerException, LogHandlerException {
        LogHandler localLog = new LogHandler();
        ArrayList<Customer> customers = localLog.populateCustomerDataset(FILE_PATH + "empty.txt");
    }

}
