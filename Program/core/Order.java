package Program.core;

import java.util.Date;

public class Order {
    int id;
    private String custCode, menuCode;
    private int numberOfTables;
    private Date preferredDate;

    public Order(String custCode, String menuCode, int numberOfTables, Date preferredDate) {
        this.custCode = custCode;
        this.menuCode = menuCode;
        this.numberOfTables = numberOfTables;
        this.preferredDate = preferredDate;
    }

    public Order(int id) {
        this.id = id;
    }

    public Order(int id, String custCode, String menuCode, int numberOfTables, Date preferredDate) {
        this.id = id;
        this.custCode = custCode;
        this.menuCode = menuCode;
        this.numberOfTables = numberOfTables;
        this.preferredDate = preferredDate;
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

}
