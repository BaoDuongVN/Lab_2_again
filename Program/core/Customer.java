package Program.core;

public class Customer {
    private String custCode, custName, custPhoneNumber, custEmail;

    public Customer(String custCode, String custName, String custPhoneNumber, String custEmail) {
        this.custCode = custCode;
        this.custName = custName;
        this.custPhoneNumber = custPhoneNumber;
        this.custEmail = custEmail;
    }

    public Customer(String custCode) {
        this.custCode = custCode;
    }

    public String getCustCode() {
        return custCode;
    }

    public String getCustName() {
        return custName;
    }

    public String getCustPhoneNumber() {
        return custPhoneNumber;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustCode(String custCode) {
        if (custCode.matches("^[C|G|K|c|g|k]\\d{4}")) {
            this.custCode = custCode;
        }
    }

    public void setCustName(String custName) {
        if (custName.matches("[A-Za-z\\s]{2,25}")) {
            this.custName = custName;
        }
    }

    public void setCustPhoneNumber(String custPhoneNumber) {
        if (custPhoneNumber.matches("[\\d]{10}")) {
            this.custPhoneNumber = custPhoneNumber;
        }
    }

    public void setCustEmail(String custEmail) {
        if (custEmail.matches("^[A-Za-z0-9_%+-]+@[A-Za-z0-9+-]+\\.[A-Za-z]{2,}$")) {
            this.custEmail = custEmail;
        }
    }

    @Override
    public String toString() {
        return custCode + ", " + custName + ", " + custEmail + ", " + custPhoneNumber;
    }
}
