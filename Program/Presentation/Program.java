package Program.Presentation;

import Program.DataObject.CustomerList;
import Program.DataObject.FeastMenuList;
import Program.DataObject.OrderList;
import Program.tools.ConsoleInputter;

public class Program {
    public static void main(String[] args) {
        /*
         * 1. Register customers.
         * 2. Update customer information.
         * 3. Search for customer information by name.
         * 4. Display feast menus.
         * 5. Place a feast order.
         * 6. Update order information.
         * 7. Save data to file.
         * 8. Display Customer or Order lists
         */
        CustomerList customerList = new CustomerList();
        customerList.loadFromFile("customers.dat");
        FeastMenuList feastMenuList = new FeastMenuList();
        feastMenuList.loadFromFile("FeastMenu.csv");
        OrderList orderList = new OrderList(feastMenuList, customerList);

        int choice = 0;
        do {
            System.out.println("---------------------------------------------------------------");
            System.out.println("1. Register customers.");
            System.out.println("2. Update customer information.");
            System.out.println("3. Search for customer information by name.");
            System.out.println("4. Display feast menus.");
            System.out.println("5. Place a feast order.");
            System.out.println("6. Update order information.");
            System.out.println("7. Save data to file.");
            System.out.println("8. Display Customer or Order lists");
            System.out.println("9. Quit menu");
            System.out.println("---------------------------------------------------------------");
            choice = ConsoleInputter.getInt("Enter your choice", 1, 9);
            switch (choice) {
                case 1:
                    customerList.registerCust();
                    break;
                case 2:
                    customerList.UpdateCust();
                    break;
                case 3:
                    customerList.searchByName();
                    break;
                case 4:
                    feastMenuList.display();
                    break;
                case 5:
                    orderList.inputOrder();
                    orderList.printFeast();
                    break;
                case 6:
                    orderList.updateOrder();
                    break;
                case 7:
                    orderList.saveFileCust("customers.dat");
                    orderList.saveFileMenu("feast_order_service.dat");
                case 8:
                    break;
                default:
                    break;
            }
        } while (choice != 9);
    }
}
