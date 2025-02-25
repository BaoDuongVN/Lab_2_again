package Program.Presentation;

import Program.DataObject.CustomerList;

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
        customerList.loadFromFile("CustomerData.txt");
        // customerList.displayCustomer();
        // customerList.registerCust();
        // customerList.UpdateCust();
        customerList.searchByName();
    }
}
