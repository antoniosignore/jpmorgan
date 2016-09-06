package com.jpmorgan.model;

import java.io.Serializable;

public class PortfolioEntry implements Serializable {

    private Portfolio portfolio;
    private Instrument instrument;
    private Integer amount = 0;

    public PortfolioEntry() {
    }

    public PortfolioEntry(Instrument instrument, Portfolio portfolio) {
        this.instrument = instrument;
        this.portfolio = portfolio;
    }

    // amount < 0 means taking short position in instrument
    public PortfolioEntry(Instrument instrument, Integer amount, Portfolio portfolio) {
        this.instrument = instrument;
        this.amount = amount;
        this.portfolio = portfolio;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public int position() {
        if (amount >= 0)
            return +1;
        else
            return -1;
    }

    public double value() {
        return instrument.getLast() * amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortfolioEntry that = (PortfolioEntry) o;

        if (!portfolio.equals(that.portfolio)) return false;
        if (!instrument.equals(that.instrument)) return false;
        return amount.equals(that.amount);

    }

    @Override
    public int hashCode() {
        int result = portfolio.hashCode();
        result = 31 * result + instrument.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PortfolioEntry{" +
                ", instrument=" + instrument +
                ", amount=" + amount +
                '}';
    }
}
