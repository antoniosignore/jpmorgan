package com.jpmorgan.test;

import com.jpmorgan.beans.Trade;
import com.jpmorgan.model.Instrument;
import com.jpmorgan.model.Portfolio;
import com.jpmorgan.model.PortfolioEntry;

import java.util.Date;
import java.util.List;

public interface PortfolioService {

    Date firstDay(Portfolio portfolio);

    Date latestDay(Portfolio portfolio);

    PortfolioEntry entry(Portfolio portfolio, Instrument instrument);

    void add(Portfolio portfolio, PortfolioEntry item);

    void add(Portfolio portfolio, Instrument instrument);

    void add(Portfolio portfolio, Instrument instrument, int Amount);

    void add(Portfolio portfolio, Trade trade);

    void invest(Portfolio portfolio, double wealth);

    // Invest wealth into portfolio according to current portfolio weights
    void invest(Portfolio portfolio, double wealth, Date date);

    Trade buy(Portfolio portfolio, Instrument instrument, int amount);

    Trade buy(Portfolio portfolio, Instrument instrument, int amount, Date date);

    Trade sell(Portfolio portfolio, Instrument instrument, int amount);

    Trade sell(Portfolio portfolio, Instrument instrument, int amount, Date date);

    Trade sellShort(Portfolio portfolio, Instrument instrument, int amount);

    Trade sellShort(Portfolio portfolio, Instrument instrument, int Amount, Date date);

    Trade buyShort(Portfolio portfolio, Instrument instrument, int Amount);

    // Buy short
    Trade buyShort(Portfolio portfolio, Instrument instrument, int Amount, Date date);

    Trade sell(Portfolio portfolio, Instrument instrument);

    // Sell everything - todo
    Trade sell(Portfolio portfolio, Instrument instrument, Date date);

    // delete instrument from portfolio
    void remove(Portfolio portfolio, Instrument instrument);

    // Return weight of this instrument in the portfolio
    // Return 0 if instrument is not in the portfolio
    double getWeight(Portfolio portfolio, Instrument instrument);

    // Return position of this instrument in the portfolio
    // Return 0 if instrument is not in the portfolio
    int position(Portfolio portfolio, Instrument instrument);

    // Return amount of this instrument in the portfolio
    // Return 0 if instrument is not in the portfolio
    int amount(Portfolio portfolio, Instrument instrument);

    double amount(Portfolio portfolio);

    // Return wealth for i-th asset in portfolio
    double wealth(Portfolio portfolio, Instrument asset, Date date);

    double getWealth(Portfolio portfolio);

    // Return wealth of portfolio
    double getWealth(Portfolio portfolio, Date date);

    // Return marked to market portfolio premium If we consider a portfolio as one
    // financial instrument, its premium is equal to its value
    double price(Portfolio portfolio, Date date);

    // Return portfolio premium If we consider a portfolio as one
    // financial instrument, its premium is equal to its value
    double premium(Portfolio portfolio);

    double m2m(Portfolio portfolio, Date date);

    // Return portfolio value
    double m2m(Portfolio portfolio);

    /*
            public double getReturn(Portfolio portfolio, Date date) {
                // getAndRemove marked to market daily return
                double price = price(portfolio, date);
                double previousPrice;
                if (price == 0) {
                    return 1;
                }
                Date previousDate = getInstrument(portfolio, 0).prevDate(date);
                if (previousDate == null) {
                    return 1;
                } else {
                    previousPrice = price(portfolio, previousDate);
                    if (previousPrice == 0) {
                        return 1;
                    } else {
                        return price / previousPrice;
                    }
                }
            }
        */
    void setInstrument(Portfolio portfolio, PortfolioEntry entry, Instrument instrument);

    void setAmount(Portfolio portfolio, PortfolioEntry entry, int amount);

    void setWealth(Portfolio portfolio, double Wealth);

    int nTransactions(Portfolio portfolio);

    int nentries(Portfolio portfolio);

    int getItemAmount(PortfolioEntry item);

    List<PortfolioEntry> getInstruments(Portfolio portfolio);
}
