package com.jpmorgan.utils;

import com.jpmorgan.beans.Trade;
import com.jpmorgan.model.Instrument;
import org.apache.commons.collections.Predicate;

import java.util.Calendar;

public class StockPredicate implements Predicate {

    int minutesRange;
    private Instrument instrument;
    private Calendar dateRange = null;

    public StockPredicate(Instrument instrument, int minutesRange) {
        this.instrument = instrument;
        this.minutesRange = minutesRange;
        dateRange = Calendar.getInstance();
        dateRange.add(Calendar.MINUTE, -minutesRange);
    }

    public boolean evaluate(Object tradeObject) {
        Trade trade = (Trade) tradeObject;

        System.out.println("instrument.getName() = " + instrument.getName());
        System.out.println("trade.getInstrument().getName() = " + trade.getInstrument().getName());
        System.out.println("dateRange.getTime() = " + dateRange.getTime());
        System.out.println("trade.getTradeDate() = " + trade.getTradeDate());

        boolean toInclude = trade.getInstrument().equals(instrument);
        if (toInclude && dateRange != null) {
            toInclude = dateRange.getTime().compareTo(trade.getTradeDate()) <= 0;
            System.out.println("dateRange.getTime().compareTo(trade.getTradeDate())<=0 = " + (dateRange.getTime().compareTo(trade.getTradeDate()) <= 0));
        }

        System.out.println("toInclude = " + toInclude);
        return toInclude;
    }
}