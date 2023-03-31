package com.task.invoice.core.helpers;

public enum RoleHelper {
    ADMIN("admin"),
    ACCOUNTANT("konyvelo"),
    USER("felhasznalo");

    private String value;
    RoleHelper(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
