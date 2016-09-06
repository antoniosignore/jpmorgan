package com.jpmorgan.enums;

public enum TradeEnum {

    SELLSHORT("Sell Short"),
    SELL("Sell"),
    HOLD("Sold"),
    BUY("Buy"),
    BUYSHORT("Buy Short");

    final String value;

    TradeEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

    public String getKey() {
        return name();
    }
}