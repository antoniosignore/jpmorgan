package com.jpmorgan.beans;

import com.jpmorgan.enums.TradeEnum;
import com.jpmorgan.model.Instrument;

import java.io.Serializable;
import java.util.Date;

public class Trade implements Serializable {

    private Instrument instrument;
    private TradeEnum tradeAction;
    private Integer amount = 0;
    private Double price = 0.0;
    private Double cost = 0D;
    private Date tradeDate;

    public Trade() {
    }

    public Trade(Instrument instrument, TradeEnum tradeAction, int amount, double price, Date tradeDate) {
        if (tradeDate == null) throw new IllegalArgumentException("date cannot be null");
        this.tradeDate = tradeDate;
        this.tradeAction = tradeAction;
        this.amount = amount;
        this.price = price;
        this.cost = 0D;
    }

    private Trade(TradeBuilder builder) {
        this.amount = builder.amount;
        this.cost = builder.cost;
        this.instrument = builder.instrument;
        this.price = builder.price;
        this.tradeAction = builder.tradeAction;
        this.tradeDate = builder.tradeDate;
    }

    public double value() {
        return amount * price;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public TradeEnum getTradeAction() {
        return tradeAction;
    }

    public void setTradeAction(TradeEnum tradeAction) {
        this.tradeAction = tradeAction;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "instrument=" + instrument +
                ", tradeAction=" + tradeAction +
                ", amount=" + amount +
                ", price=" + price +
                ", cost=" + cost +
                ", tradeDate=" + tradeDate +
                '}';
    }

    public static class TradeBuilder {

        private Instrument instrument;
        private TradeEnum tradeAction;
        private Integer amount = 0;
        private Double price = 0.0;
        private Double cost = 0D;
        private Date tradeDate;

        public TradeBuilder(Instrument instrument) {
            this.instrument = instrument;
        }

        public TradeBuilder setTradeAction(TradeEnum tradeAction) {
            this.tradeAction = tradeAction;
            return this;
        }

        public TradeBuilder setAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public TradeBuilder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public TradeBuilder setCost(Double cost) {
            this.cost = cost;
            return this;
        }

        public TradeBuilder setTradeDate(Date tradeDate) {
            this.tradeDate = tradeDate;
            return this;
        }

        public Trade build() {
            return new Trade(this);
        }
    }
}
