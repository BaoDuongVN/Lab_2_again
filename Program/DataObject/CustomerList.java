package Program.DataObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

import Program.core.Customer;
import Program.tools.ConsoleInputter;

public class CustomerList extends ArrayList<Customer> {

    public void registerCust() {
        String custCode = ConsoleInputter.getStr("Enter a customer code (C, G, K)", "^[C|G|K|c|g|k]\\d{4}",
                "Your entered code is error! Please enter again!");
        String custName = ConsoleInputter.getStr("Enter a customer name", "[A-Za-z\\s]{2,25}",
                "Your entered name is error! Please enter again!");
        String custPhoneNumber = ConsoleInputter.getStr("Enter a customer phone number", "[\\d]{10}",
                "Your entered phone number is error! Please enter again!");
        String custEmail = ConsoleInputter.getStr("Enter a customer email",
                "[A-Za-z0-9_%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                "Your entered email is error! Please enter again!");
        Customer customer = new Customer(custCode, custName, custPhoneNumber, custEmail);
        this.add(customer);
    }

    public void UpdateCust() {
        String custCode = ConsoleInputter.getStr("Enter a customer code (C, G, K)", "^[C|G|K|c|g|k]\\d{4}",
                "Your entered code is error! Please enter again!");
        int pos = 0;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCustCode().equalsIgnoreCase(custCode)) {
                pos = i;
                break;
            }
        }
        Customer cust = (pos >= 0) ? this.get(pos) : null;
        if (cust != null) {
            String custName = ConsoleInputter.getStr("Enter a customer name", "[A-Za-z\\s]{2,25}",
                    "Your entered name is error! Please enter again!");
            String custPhoneNumber = ConsoleInputter.getStr("Enter a customer phone number", "[\\d]{10}",
                    "Your entered phone number is error! Please enter again!");
            String custEmail = ConsoleInputter.getStr("Enter a customer email",
                    "[A-Za-z0-9_%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                    "Your entered email is error! Please enter again!");
            cust.setCustCode(custCode);
            cust.setCustName(custName);
            cust.setCustPhoneNumber(custPhoneNumber);
            cust.setCustEmail(custEmail);
            System.out.println("The registration has been successfull");
        } else {
            System.out.println("This customer does not exist");
        }
    }

    public void searchByName() {
        String custName = ConsoleInputter.getStr("Enter a customer name", "[A-Za-z\\s]{1,25}",
                "Your entered name is error! Please enter again!");
        List<Customer> matchingCust = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            Customer cust = this.get(i);
            if (cust.getCustName().toUpperCase().contains(custName.toUpperCase())) {
                matchingCust.add(cust);
            }
        }
        if (matchingCust.isEmpty()) {
            System.out.println("No one matches the search criteria!");
            return;
        }
        matchingCust.sort(Comparator.comparing(Customer::getCustName));
        System.out.println("--------------------------------------------------------------------");
        System.out.format("%-10s|%-20s|%-20s|%-20s\n", "Code",
                "Customer Name",
                "Phone", "Email");
        for (Customer cust : matchingCust) {
            System.out.format("%-10s|%-20s|%-20s|%-20s\n", cust.getCustCode(),
                    cust.getCustName(),
                    cust.getCustPhoneNumber(), cust.getCustEmail());
                }
        System.out.println("--------------------------------------------------------------------");
    }

    public void displayCustomer() {
        for (Customer customer : this) {
            System.out.println(customer);
        }
    }

    public void loadFromFile(String fName) {
        File f = new File(fName);
        if (!f.exists()) {
            System.out.println("File of customer does not exist"); // Thông báo lỗi
            System.exit(0); // Thoát khỏi chương trinh mà không thông báo lỗi
        }
        try {
            // Nếu file tồn tại, tiến hành mở file đọc
            FileReader fr = new FileReader(f); // Đọc file theo từng kí tự
            BufferedReader bf = new BufferedReader(fr); // Đọc file theo từng dòng

            String line;
            // Đọc từng dòng cho đén hết file
            while ((line = bf.readLine()) != null) {
                line = line.trim(); // Xoá khoảng trắng dư thừa ở đầu và cuối dòng
                if (!line.isEmpty()) {
                    StringTokenizer stk = new StringTokenizer(line, ",");
                    String custCode = stk.nextToken().trim();
                    String custName = stk.nextToken().trim();
                    String custPhoneNumber = stk.nextToken().trim();
                    String custEmail = stk.nextToken().trim();
                    Customer customer = new Customer(custCode, custName, custPhoneNumber, custEmail);

                    // thêm đối tượng vào danh sách
                    this.add(customer);
                }
            }
            // Đóng file sau khi đọc xong
            fr.close();
            bf.close();
        } catch (Exception e) {
            System.out.println(e); // In thông báo lỗi
        }
    }

}