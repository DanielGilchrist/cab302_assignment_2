package asgn2Tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.VegetarianPizza;
import asgn2Restaurant.LogHandler;

/**
 * A class that tests the methods relating to the creation of Pizza objects in
 * the asgn2Restaurant.LogHander class.
 * 
 * @author Person B
 * 
 */
@SuppressWarnings("unused")
public class LogHandlerPizzaTests {
    private static final String FILE_PATH = "./testFiles/";
    ArrayList<Pizza> pizzas;

    @Test
    @Before
    public void testLoadFile() throws PizzaException, LogHandlerException {
        pizzas = LogHandler.populatePizzaDataset(FILE_PATH + "valid_test.txt");
    }

    @Test
    public void testObjectType() {
        assertTrue(pizzas.get(0) instanceof VegetarianPizza);
        assertTrue(pizzas.get(1) instanceof MargheritaPizza);
        assertTrue(pizzas.get(2) instanceof MeatLoversPizza);
    }

    @Test
    public void testDataIntegrity() {
        assertEquals(2, pizzas.get(0).getQuantity());
        assertEquals(1, pizzas.get(1).getQuantity());
        assertEquals(3, pizzas.get(2).getQuantity());
    }

    // ------------------- Test errors in the log file ----------------------//

    @Test(expected = LogHandlerException.class)
    public void testFileNotExists() throws PizzaException, LogHandlerException {
        ArrayList<Pizza> pizzas = LogHandler.populatePizzaDataset(FILE_PATH + "broken_File.txt");
    }

    @Test(expected = LogHandlerException.class)
    public void testFileWithExtraParameters() throws PizzaException, LogHandlerException {
        ArrayList<Pizza> pizzas = LogHandler.populatePizzaDataset(FILE_PATH + "extra_parameter_first_line.txt");
    }

    @Test(expected = LogHandlerException.class)
    public void testFileWithExtraParametersSecondLine() throws PizzaException, LogHandlerException {
        ArrayList<Pizza> pizzas = LogHandler.populatePizzaDataset(FILE_PATH + "extra_parameter_second_line.txt");
    }

    @Test(expected = LogHandlerException.class)
    public void testFileWithExtraParametersLastLine() throws PizzaException, LogHandlerException {
        ArrayList<Pizza> pizzas = LogHandler.populatePizzaDataset(FILE_PATH + "extra_parameter_Last_line.txt");
    }

    @Test
    public void testFileWithExtraNewLastLine() throws CustomerException, LogHandlerException {
        ArrayList<Pizza> pizzas = new ArrayList<>();
        try {
            pizzas = LogHandler.populatePizzaDataset(FILE_PATH + "extra_parameter_Last_line.txt");
        } catch (Exception e) {
            e.getMessage();
        }
        assertTrue(pizzas.isEmpty());
    }

    @Test(expected = LogHandlerException.class)
    public void testFileFirstLineNoReturn() throws PizzaException, LogHandlerException {
        ArrayList<Pizza> pizzas = LogHandler.populatePizzaDataset(FILE_PATH + "no_return_first_line.txt");
    }

    @Test(expected = LogHandlerException.class)
    public void testErrorInLastLine() throws PizzaException, LogHandlerException {
        ArrayList<Pizza> pizzas = LogHandler.populatePizzaDataset(FILE_PATH + "last_Line_return.txt");
    }

    // ----------------------------- empty log ------------------------------//
    @Test(expected = LogHandlerException.class)
    public void testEmptyLogFile() throws PizzaException, LogHandlerException {
        ArrayList<Pizza> pizzas = LogHandler.populatePizzaDataset(FILE_PATH + "empty.txt");
    }

    @Test(expected = LogHandlerException.class)
    public void testNoCommaFile() throws PizzaException, LogHandlerException {
        ArrayList<Pizza> pizzas = LogHandler.populatePizzaDataset(FILE_PATH + "no_comma.txt");
    }
}
