package com.example.basic.model;

public enum Role {
    ADMIN("Admin"), USER("User"), SALE("Sale");

    private final String displayValue;

    Role(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
