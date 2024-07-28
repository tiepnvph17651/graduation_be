package com.example.demo.enums;

public enum OrderEnum {
    PENDING("P"),
    DONE("D"),
    CANCEL("C");

    private final String value;

    OrderEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
