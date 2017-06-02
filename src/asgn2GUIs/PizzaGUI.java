package asgn2GUIs;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.PizzaRestaurant;

/**
 * This class is the graphical user interface for the rest of the system.
 * Currently it is a �dummy� class which extends JFrame and implements Runnable
 * and ActionLister. It should contain an instance of an
 * asgn2Restaurant.PizzaRestaurant object which you can use to interact with the
 * rest of the system. You may choose to implement this class as you like,
 * including changing its class signature � as long as it maintains its core
 * responsibility of acting as a GUI for the rest of the system. You can also
 * use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 * 
 * 
 * @author Person A and Person B
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {

    private static final int COL = 5;
    private static final int ROW = 10;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 600;

    private PizzaRestaurant restaurant;

    private JPanel centrePanel;
    private JPanel topPanel;
    private JPanel buttomPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;

    private JButton load;
    private JButton profitCalculator;
    private JButton distanceCalculator;
    private JButton reset;
    private JButton loadCustomer;
    private JButton loadPizza;

    private JScrollPane customerScrollPane;
    private JScrollPane pizzaTable;
    private JLabel profitLabel;
    private JLabel distanceLabel;
    private JLabel message;

    private JTable customerDataTable;
    private JTable pizzaDataTable;

    private DefaultTableModel tableModel1;
    private DefaultTableModel tableModel2;

    private JFileChooser file = null;

    private String[] customerColNames = { "Customer Name", "Mobile Number", "Customer Type", "Location", "Distance" };
    private Object[][] customerData; // = new Object[ROW][COL];

    private String[] pizzaColNames = { "Pizza Type", "Quantity", "Order Price", "Order Cost", "Order Profit" };
    private Object[][] pizzaData; // = new Object[ROW][COL];

    private BufferedReader input = null;

    /**
     * Creates a new Pizza GUI with the specified title
     * 
     * @param title
     *            - The title for the supertype JFrame
     */
    public PizzaGUI(String title) {
        super(title);
        restaurant = new PizzaRestaurant();
    }

    @Override
    public void run() {
        RunGUI();
    }

    // -------------------------- event handlers --------------------------- //
    private void load() {
        JButton open = new JButton();
        file = new JFileChooser();
        file.setCurrentDirectory(new File("./logs"));
        file.setFileFilter(new FileNameExtensionFilter("TEXT FILE", "txt"));
        file.setDialogTitle("Select a file");
        file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if (file.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
            try {
                restaurant.processLog(file.getSelectedFile().toString());

                message.setForeground(new Color(9, 140, 50));
                message.setText("File was imported successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString()/* "Opening file failed!" */);
            }
        } else {
            // No file was selected
        }
    }

    private void reset() {

        tableModel1.setRowCount(0);
        tableModel2.setRowCount(0);
        profitLabel.setText("Total profit for this day was: ");
        distanceLabel.setText("Total distance travel for this day was: ");
        message.setForeground(new Color(9, 140, 50));
        message.setText("Application was successfully reset!");
    }

    private void calculateDistance() {
        JOptionPane.showMessageDialog(null, "Calculate Distance");
    }

    private void calculateProfit() {
        JOptionPane.showMessageDialog(null, restaurant.getTotalProfit());
    }

    private void getCustomerData() throws CustomerException {
        for (int i = 0; i < restaurant.getNumCustomerOrders(); i++) {
            Customer customer = restaurant.getCustomerByIndex(i);

            String name = customer.getName();
            String mobileNumber = customer.getMobileNumber();
            String type = customer.getCustomerType();
            String location = String.format("(%d, %d)", customer.getLocationX(), customer.getLocationY());
            double distance = customer.getDeliveryDistance();
            Object[] row = { name, mobileNumber, type, location, Double.toString(distance) };
            tableModel1.addRow(row);
        }

    }

    private void getPizzaData() throws PizzaException {
        for (int i = 0; i < restaurant.getNumPizzaOrders(); i++) {
            Pizza pizza = restaurant.getPizzaByIndex(i);

            String type = pizza.getPizzaType();
            int quantity = pizza.getQuantity();
            double price = pizza.getOrderPrice();
            double cost = pizza.getOrderCost();
            double profit = pizza.getOrderProfit();
            Object[] row = { type, Integer.toString(quantity), Double.toString(price), Double.toString(cost),
                    Double.toString(profit) };
            tableModel2.addRow(row);
        }

    }

    // -------------------------- event handlers --------------------------- //
    /*
     * Runs everything from the GUI here
     */
    private void RunGUI() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setDefaultLookAndFeelDecorated(false);

        centrePanel = createPanel(Color.WHITE);
        topPanel = createPanel(Color.LIGHT_GRAY);
        buttomPanel = createPanel(Color.LIGHT_GRAY);
        buttomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        leftPanel = createPanel(Color.LIGHT_GRAY);
        rightPanel = createPanel(Color.LIGHT_GRAY);

        getContentPane().add(centrePanel, BorderLayout.CENTER);
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(buttomPanel, BorderLayout.SOUTH);
        getContentPane().add(leftPanel, BorderLayout.WEST);
        getContentPane().add(rightPanel, BorderLayout.EAST);

        load = createButton("Load log file");
        profitCalculator = createButton("Total profit");
        distanceCalculator = createButton("Total distance travelled");
        reset = createButton("Reset");

        layoutMiddleContents();
        layoutButtonPanel();
        repaint();
        setVisible(true);
    }

    /*
     * Sets up everything in the middle section
     */
    private void layoutMiddleContents() {
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.PAGE_AXIS));
        JPanel centre = createPanel(Color.WHITE);
        JPanel controls = createPanel(Color.WHITE);
        centre.setLayout(new BoxLayout(centre, BoxLayout.PAGE_AXIS));
        controls.setLayout(new BoxLayout(centre, BoxLayout.LINE_AXIS));

        customerScrollPane = setCustomerTable(customerColNames, customerData);
        pizzaTable = setPizzaTable(pizzaColNames, pizzaData);
        profitLabel = createLabel("Total profit for this day was: ");
        distanceLabel = createLabel("Total distance travel for this day was: ");
        message = new JLabel();
        message.setForeground(Color.blue);
        message.setFont(new Font("Consolas", Font.BOLD, 14));
        message.setText("Welcome");

        // Add titles
        JLabel customerTitle = new JLabel("Customers info:");
        JLabel pizzaTitle = new JLabel("Pizzas info:");

        loadCustomer = createButton("Analyse customer log");
        loadPizza = createButton("Analyse pizza log");
        controls.setLayout(new BorderLayout());
        controls.add(loadCustomer, BorderLayout.EAST);
        controls.add(loadPizza, BorderLayout.WEST);

        centre.add(Box.createRigidArea(new Dimension(0, 5)));
        centre.add(message);
        centre.add(Box.createRigidArea(new Dimension(0, 5)));
        centre.add(customerTitle);
        centre.add(customerScrollPane);
        centre.add(Box.createRigidArea(new Dimension(0, 5)));
        centre.add(pizzaTitle);
        centre.add(pizzaTable);
        centre.add(Box.createRigidArea(new Dimension(0, 10)));
        centre.add(profitLabel);
        centre.add(distanceLabel);
        centre.add(Box.createRigidArea(new Dimension(0, 5)));
        centre.add(controls);

        centrePanel.add(centre);
        centrePanel.add(controls);
    }

    private JScrollPane setCustomerTable(String[] colName, Object[][] data) {
        tableModel1 = new DefaultTableModel(data, colName) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        customerDataTable = new JTable(tableModel1);
        // customerDataTable.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(customerDataTable);
        customerDataTable.setFillsViewportHeight(true);
        return scrollPane;
    }

    private JScrollPane setPizzaTable(String[] colName, Object[][] data) {
        tableModel2 = new DefaultTableModel(data, colName) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        pizzaDataTable = new JTable(tableModel2);
        // pizzaDataTable.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(pizzaDataTable);
        pizzaDataTable.setFillsViewportHeight(true);
        return scrollPane;
    }

    private JLabel createLabel(String s) {
        JLabel lb = new JLabel();
        lb.setText(s);
        lb.setFont(new Font("Consolas", Font.PLAIN, 13));
        return lb;
    }

    private JButton createButton(String str) {
        JButton btn = new JButton();
        btn.setText(str);
        btn.addActionListener(this);
        return btn;
    }

    private JPanel createPanel(Color c) {
        JPanel panel = new JPanel();
        panel.setBackground(c);
        return panel;
    }

    private void layoutButtonPanel() {
        GridBagLayout layout = new GridBagLayout();
        buttomPanel.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 100;

        addToPanel(buttomPanel, load, constraints, 0, 2, 2, 1);
        addToPanel(buttomPanel, profitCalculator, constraints, 3, 2, 2, 1);
        addToPanel(buttomPanel, distanceCalculator, constraints, 5, 2, 2, 1);
        addToPanel(buttomPanel, reset, constraints, 7, 2, 2, 1);
    }

    /**
     *
     * A convenience method to add a component to given grid bag layout
     * locations. Code due to Cay Horstmann
     * 
     * @param c
     *            the component to add
     * @param constraints
     *            the grid bag constraints to us e
     * @param x
     *            the x grid position
     * @param y
     *            the y grid position
     * @param w
     *            the grid width of the component
     * @param h
     *            the grid height of the component
     */
    private void addToPanel(JPanel jPanel, Component c, GridBagConstraints constraints, int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jPanel.add(c, constraints);
    }

    private Object[][] createEmptyField() {
        final int rows = 10;
        final int cols = 5;
        Object[][] obj = new Object[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                obj[i][j] = "";
            }
        }
        return obj;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object src = event.getSource();
        if (src == load) {
            load();
        } else if (src == profitCalculator) {
            calculateProfit();
        } else if (src == distanceCalculator) {
            calculateDistance();
        } else if (src == reset) {
            reset();
        } else if (src == loadCustomer) {
            try {
                getCustomerData();
            } catch (CustomerException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        } else if (src == loadPizza) {
            try {
                getPizzaData();
            } catch (PizzaException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

}
