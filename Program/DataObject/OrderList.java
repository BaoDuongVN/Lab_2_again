package Program.DataObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import Program.core.Customer;
import Program.core.FeastMenu;
import Program.core.Order;
import Program.tools.ConsoleInputter;

public class OrderList extends ArrayList<Order> {
    CustomerList customerList;
    FeastMenuList feastMenuList;
    List<Customer> matchingCust = new ArrayList<>();
    List<FeastMenu> matchingMenu = new ArrayList<>();
    List<Order> res = new ArrayList<>();
    String custCode;
    String menuCode;
    int numberOfTables;
    Date preferredDate;
    Customer cust;
    FeastMenu menu;
    int id;

    public OrderList(FeastMenuList feastMenuList, CustomerList customerList) {
        super();
        this.feastMenuList = feastMenuList;
        this.customerList = customerList;
    }

    public FeastMenuList getF() {
        return feastMenuList;
    }

    public CustomerList getC() {
        return customerList;
    }

    public void inputOrder() {
        custCode = ConsoleInputter.getStr("Enter a customer code (C, G, K)", "^[C|G|K|c|g|k]\\d{4}",
                "Your entered code is error! Please enter again!");
        menuCode = ConsoleInputter.getStr("Enter the set menu code (5 characters)", "^.{5}$",
                "The entered code is error. Please enter again");
        numberOfTables = ConsoleInputter.getInt("Enter the number of tables", 1, 100);
        do {
            preferredDate = ConsoleInputter.getDate(
                    "Enter the preferred event date (dd/MM/yyyy): ",
                    "dd/MM/yyyy");
            if (preferredDate.before(new Date())) {
                System.out.println("The date cannot be in the past. Please enter again.");
            }
        } while (preferredDate.before(new Date()));
        if (isDuplicate(custCode, menuCode, preferredDate)) {
            System.out.println("Duplicate data!");
        }

        cust = customerList.findCustomerByCode(custCode);
        menu = feastMenuList.findMenuByCode(menuCode);
        if (cust == null || menu == null) {
            System.out.println("Customer or menu not found");
            return;
        }

        Order order = new Order(custCode, menuCode, numberOfTables, preferredDate);
        this.add(order);
        matchingCust.add(cust);
        matchingMenu.add(menu);
    }

    public boolean isDuplicate(String custCode, String menuCode, Date preferredDate) {
        for (Order order : this) {
            if (order.getCustCode().equalsIgnoreCase(custCode) ||
                    order.getMenuCode().equalsIgnoreCase(menuCode) ||
                    order.getPreferredDate().compareTo(preferredDate) == 0) {
                return true;
            }
        }
        return false;
    }

    public void printFeast() {
        Random random = new Random();
        Order o = new Order(random.nextInt(1, 100));
        id = o.getId();
        System.out.println("--------------------------------------------------------------------------------");
        System.out.format("Customer order information [Order ID: %d]\n", id);
        System.out.println("--------------------------------------------------------------------------------");
        for (Customer cus : matchingCust) {
            System.out.format("%-30s: %-15s\n", "Code", cus.getCustCode());
            System.out.format("%-30s: %-15s\n", "Customer name", cus.getCustName());
            System.out.format("%-30s: %-15s\n", "Phone number", cus.getCustPhoneNumber());
            System.out.format("%-30s: %-15s\n", "Email", cus.getCustEmail());
            System.out.println("--------------------------------------------------------------------------------");
        }
        for (FeastMenu men : matchingMenu) {
            System.out.format("%-30s: %-15s\n", "Code of Set Menu", men.getMenuCode());
            System.out.format("%-30s: %-15s\n", "Set menu name", men.getMenuName());
            System.out.format("%-30s: %-15s\n", "Event date", ConsoleInputter.dateStr(preferredDate, "dd/MM/yyyy"));
            System.out.format("%-30s: %-15s\n", "Number of tables", numberOfTables);
            System.out.format("%-30s: %-15s\n", "Price", men.getMenuPrice());
            System.out.println("Ingredients");
            System.out.println(men.getMenuIngredients().replaceAll("#", "\n").replaceAll("\"", ""));
            System.out.println("--------------------------------------------------------------------------------");
            System.out.format("%-30s: %-15s\n", "Total", men.getMenuPrice() * numberOfTables);
            System.out.println("--------------------------------------------------------------------------------");
        }
        Order order = new Order(id, custCode, menuCode, numberOfTables, preferredDate);
        res.add(order);
    }

    public void updateOrder() {
        int id = ConsoleInputter.getInt("Enter the order id", 1, 100);
        int pos = 0;
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i).getId() == id) {
                pos = i;
                break;
            }
        }
        Order order = (pos >= 0) ? res.get(pos) : null;
        if (order != null) {
            menuCode = ConsoleInputter.getStr("Enter the set menu code (5 characters)", "^.{5}$",
                    "The entered code is error. Please enter again");
            numberOfTables = ConsoleInputter.getInt("Enter the number of tables", 1, 100);
            do {
                preferredDate = ConsoleInputter.getDate(
                        "Enter the preferred event date (dd/MM/yyyy): ",
                        "dd/MM/yyyy");
                if (preferredDate.before(new Date())) {
                    System.out.println("The date cannot be in the past. Please enter again.");
                }
            } while (preferredDate.before(new Date()));
            order.setMenuCode(menuCode);
            order.setPreferredDate(preferredDate);
            order.setNumberOfTables(numberOfTables);
        } else {
            System.out.println("This order does not exist");
        }
    }

    public void saveFileCust(String fname) {
        try {
            File file = new File(fname);
            try (PrintWriter pw = new PrintWriter(new java.io.FileWriter(file, true))) {
                for (Customer cust : matchingCust) {
                    pw.println(cust.getCustCode() + ", " + cust.getCustName() + ", " +
                            cust.getCustPhoneNumber() + ", " + cust.getCustEmail());
                }
                System.out.println("Data successfully");
            }
        } catch (Exception e) {
            System.out.println("Error while saving file: " + e.getMessage());
        }
    }

    public void saveFileMenu(String fname) {
        try {
            File file = new File(fname);

            try (PrintWriter pw = new PrintWriter(new java.io.FileWriter(file, true))) {
                for (FeastMenu feastMenu : matchingMenu) {
                    pw.println(id + ", " + custCode + ", " +
                            feastMenu.getMenuCode() + ", " + feastMenu.getMenuPrice() + ", " +
                            numberOfTables);
                }
                System.out.println("Data successfully");
            }
        } catch (Exception e) {
            System.out.println("Error while saving file: " + e.getMessage());
        }
    }
}
