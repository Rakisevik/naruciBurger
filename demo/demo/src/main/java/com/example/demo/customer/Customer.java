package com.example.demo.customer;

public class Customer {
    private final Integer customerId;
    private final String customerName;
    private final String customerSurname;
    private final String customerNum;
    private final String customerMail;

    public Customer(Integer customerId, String customerName, String customerSurname, String customerNum, String customerMail) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.customerNum = customerNum;
        this.customerMail = customerMail;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerSurname='" + customerSurname + '\'' +
                ", customerNum='" + customerNum + '\'' +
                ", customerMail='" + customerMail + '\'' +
                '}';
    }
}
