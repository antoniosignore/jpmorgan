package com.jpmorgan.enums;

public enum StockTypeEnum {

    PREFERRED("Preferred"),
    COMMON("Common");

    final String value;

    StockTypeEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

}