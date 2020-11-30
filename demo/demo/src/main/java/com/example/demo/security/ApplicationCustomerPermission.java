package com.example.demo.security;

public enum ApplicationCustomerPermission {
    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write"),
    BURGER_READ("burger:read"),
    BURGER_WRITE("burger:write");

    private final String permission;

    ApplicationCustomerPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
