package Program.core;

import java.util.Date;

public class Order {
    int id;
    private String custCode, menuCode;
    private int price;
    private int numberOfTables;
    private Date preferredDate;
    private int totalPrice;

    public Order(String custCode, String menuCode, int numberOfTables, Date preferredDate) {
        this.custCode = custCode;
        this.menuCode = menuCode;
        this.numberOfTables = numberOfTables;
        this.preferredDate = preferredDate;
    }

    public Order(int id) {
        this.id = id;
    }

    public Order(int id, String custCode, String menuCode, int price, int numberOfTables, Date preferredDate,
            int totalPrice) {
        this.id = id;
        this.custCode = custCode;
        this.menuCode = menuCode;
        this.price = price;
        this.numberOfTables = numberOfTables;
        this.preferredDate = preferredDate;
        this.totalPrice = totalPrice;
    }

    public Order(Customer cust, FeastMenu menu) {
        super();
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        if (custCode.matches("^[CGKcgk]\\d{4}")) {
            this.custCode = custCode;
        }
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

    public Date getPreferredDate() {
        return preferredDate;
    }

    public void setPreferredDate(Date preferredDate) {
        this.preferredDate = preferredDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

}
