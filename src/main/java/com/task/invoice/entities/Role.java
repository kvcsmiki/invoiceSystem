package com.task.invoice.entities;

public enum Role {
    ADMIN("Adminisztrátor"),
    ACCOUNTANT("Könyvelő"),
    USER("Felhasználó");

    private String value;

    Role(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
