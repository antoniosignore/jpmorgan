package com.jpmorgan.beans;


import com.jpmorgan.enums.PriceEnum;

import java.io.Serializable;
import java.util.Date;

import static com.jpmorgan.enums.PriceEnum.TYPICALPRICE;

public class Daily implements Serializable {

    private Date dailydate;
    private int volume = 0;
    private double high = 0;
    private double low = 0;
    private double openprice = 0;
    private double closeprice = 0;

    // this is optional
    private int openInterest;

    public Daily() {
    }

    private Daily(DailyBuilder builder) {

        this.closeprice = builder.closeprice;
        this.high = builder.high;
        this.low = builder.low;
        this.openprice = builder.openprice;
        this.volume = builder.volume;
        this.dailydate = builder.dailydate;
        this.openInterest = builder.openInterest;
    }

    public double price() {
        return price(TYPICALPRICE);
    }

    public double price(PriceEnum Option) {
        switch (Option) {
            case HIGH:
                return high;
            case LOW:
                return low;
            case OPEN:
                return openprice;
            case CLOSE:
                return closeprice;
            case VOLUME:
                return volume;
            case MEDIANPRICE:
                return (high + low) / 2.0;
            case TYPICALPRICE:
                return (high + low + closeprice) / 3.0;
            case WEIGHTEDPRICE:
                return (high + low + 2.0 * closeprice) / 4.0;
            case AVERAGEPRICE:
                return (high + low + closeprice + openprice) / 4.0;
            case LOGAVERAGEPRICE:
                return Math.log((high + low + closeprice + openprice) / 4.0);
        }
        return 0D;
    }

    public Date getDailydate() {
        return dailydate;
    }

    public int getVolume() {
        return volume;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getOpenprice() {
        return openprice;
    }

    public double getCloseprice() {
        return closeprice;
    }

    public int getOpenInterest() {
        return openInterest;
    }

    @Override
    public String toString() {
        return "Daily{" +
                "dailydate=" + dailydate +
                ", volume=" + volume +
                ", high=" + high +
                ", low=" + low +
                ", openprice=" + openprice +
                ", closeprice=" + closeprice +
                ", openInterest=" + openInterest +
                '}';
    }

    public static class DailyBuilder {

        private Date dailydate;
        private int volume = 0;
        private double high = 0;
        private double low = 0;
        private double openprice = 0;
        private double closeprice = 0;
        private int openInterest;

        public DailyBuilder(Date d,
                            double high,
                            double low,
                            double open,
                            double close,
                            int volume) {
            this.closeprice = close;
            this.high = high;
            this.low = low;
            this.openprice = open;
            this.volume = volume;
            this.dailydate = d;
        }

        // this is optional (only for optons. We keep it out of the builder
        public DailyBuilder setOpenInterest(int openInterest) {
            this.openInterest = openInterest;
            return this;
        }

        public Daily build() {
            return new Daily(this);
        }

    }
}
