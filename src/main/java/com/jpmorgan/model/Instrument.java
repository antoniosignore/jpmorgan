package com.jpmorgan.model;

import com.jpmorgan.beans.Daily;
import com.jpmorgan.enums.PriceEnum;
import com.jpmorgan.test.GenericTimeSeries;
import com.jpmorgan.test.TimeSeries;
import com.jpmorgan.utils.DateUtils;

import java.util.Date;

import static com.jpmorgan.enums.PriceEnum.*;

public class Instrument {

    private GenericTimeSeries<Daily> dailyarray;

    private String name;

    private TimeSeries priceSeries = null;
    private TimeSeries returnSeries = null;
    private TimeSeries logReturnSeries = null;
    private TimeSeries highSeries = null;
    private TimeSeries lowSeries = null;
    private TimeSeries openSeries = null;
    private TimeSeries closeSeries = null;
    private TimeSeries volumeSeries = null;
    private TimeSeries volumeReturnSeries = null;
    private TimeSeries volumeLogReturnSeries = null;

    private double marketSpotShift = 1.0;
    private double marketVolatilityShift = 1.0;
    private double delta = 1D;
    private double spot;
    private double volatility;
    private boolean isSpotFixed;
    private boolean isVolatilityFixed = false;
    private Date tempToday = new Date();
    private double tempSpot;
    private double tempVolatility;
    private boolean isTempSpotFixed = false;
    private boolean isTempVolatilityFixed = false;

    private Date lowerRangeDate;
    private Date upperRangeDate;

    public Instrument(String name) {
        isSpotFixed = false;
        isVolatilityFixed = false;
        priceSeries = new TimeSeries("PriceSeries");
        returnSeries = new TimeSeries("ReturnSeries");
        logReturnSeries = new TimeSeries("LogReturnSeries");
        highSeries = new TimeSeries("HighSeries");
        lowSeries = new TimeSeries("LowSeries");
        openSeries = new TimeSeries("OpenSeries");
        closeSeries = new TimeSeries("CloseSeries");
        volumeSeries = new TimeSeries("VolumeSeries");
        volumeReturnSeries = new TimeSeries("VolumeReturnSeries");
        volumeLogReturnSeries = new TimeSeries("VolumeLogReturnSeries");
        this.name = name;
        dailyarray = new GenericTimeSeries<>();
    }

    public GenericTimeSeries<Daily> getDailyarray() {
        return dailyarray;
    }

    public void setDailyarray(GenericTimeSeries<Daily> dailyarray) {
        this.dailyarray = dailyarray;
    }

    public TimeSeries getPriceSeries() {
        return priceSeries;
    }

    public void setPriceSeries(TimeSeries priceSeries) {
        this.priceSeries = priceSeries;
    }

    public TimeSeries getReturnSeries() {
        return returnSeries;
    }

    public void setReturnSeries(TimeSeries returnSeries) {
        this.returnSeries = returnSeries;
    }

    public TimeSeries getLogReturnSeries() {
        return logReturnSeries;
    }

    public void setLogReturnSeries(TimeSeries logReturnSeries) {
        this.logReturnSeries = logReturnSeries;
    }

    public TimeSeries getHighSeries() {
        return highSeries;
    }

    public void setHighSeries(TimeSeries highSeries) {
        this.highSeries = highSeries;
    }

    public TimeSeries getLowSeries() {
        return lowSeries;
    }

    public void setLowSeries(TimeSeries lowSeries) {
        this.lowSeries = lowSeries;
    }

    public TimeSeries getOpenSeries() {
        return openSeries;
    }

    public void setOpenSeries(TimeSeries openSeries) {
        this.openSeries = openSeries;
    }

    public TimeSeries getCloseSeries() {
        return closeSeries;
    }

    public void setCloseSeries(TimeSeries closeSeries) {
        this.closeSeries = closeSeries;
    }

    public TimeSeries getVolumeSeries() {
        return volumeSeries;
    }

    public void setVolumeSeries(TimeSeries volumeSeries) {
        this.volumeSeries = volumeSeries;
    }

    public TimeSeries getVolumeReturnSeries() {
        return volumeReturnSeries;
    }

    public void setVolumeReturnSeries(TimeSeries volumeReturnSeries) {
        this.volumeReturnSeries = volumeReturnSeries;
    }

    public TimeSeries getVolumeLogReturnSeries() {
        return volumeLogReturnSeries;
    }

    public void setVolumeLogReturnSeries(TimeSeries volumeLogReturnSeries) {
        this.volumeLogReturnSeries = volumeLogReturnSeries;
    }

    public double getMarketSpotShift() {
        return marketSpotShift;
    }

    public void setMarketSpotShift(double marketSpotShift) {
        this.marketSpotShift = marketSpotShift;
    }

    public double getMarketVolatilityShift() {
        return marketVolatilityShift;
    }

    public void setMarketVolatilityShift(double marketVolatilityShift) {
        this.marketVolatilityShift = marketVolatilityShift;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public double getVolatility() {
        return volatility;
    }

    /**
     * Set volatility value
     */
    public void setVolatility(double v) {
        volatility = v;
        isVolatilityFixed = true;
    }

    public boolean isSpotFixed() {
        return isSpotFixed;
    }

    public void setSpotFixed(boolean spotFixed) {
        isSpotFixed = spotFixed;
    }

    public boolean isVolatilityFixed() {
        return isVolatilityFixed;
    }

    public void setVolatilityFixed(boolean volatilityFixed) {
        isVolatilityFixed = volatilityFixed;
    }

    public Date getTempToday() {
        return tempToday;
    }

    public void setTempToday(Date tempToday) {
        this.tempToday = tempToday;
    }

    public double getTempSpot() {
        return tempSpot;
    }

    public void setTempSpot(double tempSpot) {
        this.tempSpot = tempSpot;
    }

    public double getTempVolatility() {
        return tempVolatility;
    }

    public void setTempVolatility(double tempVolatility) {
        this.tempVolatility = tempVolatility;
    }

    public boolean isTempSpotFixed() {
        return isTempSpotFixed;
    }

    public void setTempSpotFixed(boolean tempSpotFixed) {
        isTempSpotFixed = tempSpotFixed;
    }

    public boolean isTempVolatilityFixed() {
        return isTempVolatilityFixed;
    }

    public void setTempVolatilityFixed(boolean tempVolatilityFixed) {
        isTempVolatilityFixed = tempVolatilityFixed;
    }

    public Date getLowerRangeDate() {
        return lowerRangeDate;
    }

    public void setLowerRangeDate(Date lowerRangeDate) {
        this.lowerRangeDate = lowerRangeDate;
    }

    public Date getUpperRangeDate() {
        return upperRangeDate;
    }

    public void setUpperRangeDate(Date upperRangeDate) {
        this.upperRangeDate = upperRangeDate;
    }

    public void setRangeBounds(Date lowerDate, Date upperDate) {

        if (lowerRangeDate == null) lowerRangeDate = lowerDate;
        if (upperRangeDate == null) upperRangeDate = upperDate;

        if (lowerDate != null)
            if (DateUtils.isLess(lowerDate, this.lowerRangeDate))
                lowerRangeDate = lowerDate;
        if (upperDate != null)
            if (DateUtils.isGreater(upperDate, this.upperRangeDate)) upperRangeDate = upperDate;
    }

    public boolean inRange(Date date) {
        boolean b1 = DateUtils.isGreaterEqual(date, lowerRangeDate);
        boolean b2 = DateUtils.isLessEqual(date, upperRangeDate);
        boolean b3 = b1 && b2;
        return b3;
    }

    public double S() {
        return spot();
    }

    public double historicalSpot() {
        double ret = 0;
        ret = historicalSpot(new Date());
        return ret;
    }

    public double historicalSpot(Date d) {
        return getPrice(d);
    }

    public double historicalVolatility() {
        return getStandardDeviation(LOGRETURN) * Math.sqrt(365.0);
    }

    /**
     * Return volatility. In case of derivative instruments usually return volatility of the underlying instrument
     * Volatility is calculated as annualized standard deviation of instrument daily log returns
     *
     * @return Return fixed value if volatility is set with setVolatility() call else return historical volatility
     */

    /**
     * Return spot premium. In case of derivative instruments usually return spot premium of the underlying instrument
     *
     * @return Return fixed value if spot premium is set with setSpot() call else return last historical spot value
     */
    public double spot() {
        if (isSpotFixed) {
            return marketSpotShift * getSpot();
        } else {
            return marketSpotShift * historicalSpot();
        }
    }

    public void resetSpot() {
        isSpotFixed = false;
    }

    public void resetVolatility() {
        isVolatilityFixed = false;
    }

    public void storeSettings() {
        tempToday = new Date();
        tempSpot = spot;
        tempVolatility = volatility;
        isTempSpotFixed = isSpotFixed;
        isTempVolatilityFixed = isVolatilityFixed;
    }

    public void restoreSettings() {
        DateUtils.setToday(tempToday);
        spot = tempSpot;
        volatility = tempVolatility;
        isSpotFixed = isTempSpotFixed;
        isVolatilityFixed = isTempVolatilityFixed;
    }

    public void addDaily(Daily daily) {
        lowerRangeDate = (DateUtils.min(daily.getDailydate(), lowerRangeDate));
        upperRangeDate = (DateUtils.max(daily.getDailydate(), upperRangeDate));
        dailyarray.put(daily.getDailydate(), daily);
    }

    public void addDaily(Date date, double high, double low, double open, double close, int volume, int openInterest) {
        lowerRangeDate = DateUtils.min(date, lowerRangeDate);
        upperRangeDate = DateUtils.max(date, upperRangeDate);

        dailyarray.put(date, new Daily.DailyBuilder(
                date, high, low, open, close, volume)
                .build());
    }

    public boolean isDataAvailable(Date date) {
        return dataAvailable(date);
    }

    public Daily getDaily(Date date) {
        return daily(date);
    }

    public double premium() {
        return price(new Date(), TYPICALPRICE);
    }

    public double getPrice(Date date) {
        return getPrice(date, TYPICALPRICE);
    }

    /**
     * Option TYPICALPRICE, MEDIANPRICE , WEIGHTEDPRICE ,AVERAGEPRICE, LOGAVERAGEPRICE;
     */
    public double getPrice(Date date, PriceEnum Option) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        return value(date, Option);
    }

    public double getClose(Date date) {
        return dailyarray.get(date).getCloseprice();
    }

    public double getReturn(Date date) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        return re(date);
    }

    public double getLogReturn(Date date) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        return logReturn(date);
    }

    public int getVolume(Date date) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        return dailyarray.get(date).getVolume();
    }

    public double value(Date date, PriceEnum option) {
        if (dailyarray.get(date) == null)
            return getLast(date);
        switch (option) {
            case HIGH:
                return high(date);
            case LOW:
                return low(date);
            case OPEN:
                return open(date);
            case CLOSE:
                return close(date);
            case VOLUME:
                return volume(date);
            case PRICE:
                return price(date);
            case MEDIANPRICE:
            case WEIGHTEDPRICE:
            case TYPICALPRICE:
                return dailyarray.get(date).price(option);
            case RETURN:
                return getReturn(date);
            case LOGRETURN:
                return getLogReturn(date);
            default:
                return close(date);
        }
    }

    // todo - this is smelly
    public double getLast(Date date) {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        Daily daily = getDaily(date);
        while (daily == null) {
            daily = getPrevDaily(date);
        }
        if (daily != null) {
            return daily.getCloseprice();
        } else {
            return 0;
        }
    }

    public double getLast() {
        Date lastDailyDate = lastDay();
        return getLast(lastDailyDate);
    }

    public TimeSeries priceSeries() {
        priceSeries.clear();
        double lastPrice = 0;
        boolean isFirst = true;
        for (Date treeKey : dailyarray.keySet()) {
            Daily daily = dailyarray.get(treeKey);
            if (isFirst) {
                lastPrice = daily.price();
                priceSeries.add(treeKey, 0);
                isFirst = false;
            } else {
                priceSeries.add(treeKey, lastPrice);
            }
        }
        return priceSeries;

    }

    public TimeSeries series(PriceEnum what) {
        return series(what, null, null);
    }

    public TimeSeries series(PriceEnum what, Date firstD, Date lastD) {
        if (firstD == null) firstD = firstDate();
        if (lastD == null) lastD = lastDate();
        if (what == HIGH) return timeSeries(HIGH, firstD, lastD);
        else if (what == LOW) return timeSeries(LOW, firstD, lastD);
        else if (what == OPEN) return timeSeries(OPEN, firstD, lastD);
        else if (what == CLOSE) return timeSeries(CLOSE, firstD, lastD);
        else if (what == VOLUME) return timeSeries(VOLUME, firstD, lastD);
        return null;
    }

    public TimeSeries highSeries() {
        highSeries = timeSeries(HIGH, firstDay(), lastDay());
        return highSeries;
    }

    public TimeSeries lowSeries() {
        lowSeries = timeSeries(LOW, firstDay(), lastDay());
        return lowSeries;
    }

    public TimeSeries closeSeries() {
        closeSeries = timeSeries(CLOSE, firstDailyDate(), lastDay());
        return closeSeries;
    }

    public TimeSeries openSeries(Date firstDate, Date lastDate) {
        openSeries = timeSeries(OPEN, firstDay(), lastDay());
        return openSeries;
    }

    public TimeSeries volumeSeries() {
        volumeSeries = timeSeries(VOLUME, firstDay(), lastDay());
        return volumeSeries;
    }

    public TimeSeries buildReturnSeries(Date firstDate, Date lastDate) {
        double lastPrice = 0;
        boolean isFirst = true;
        for (Date treeKey : dailyarray.keySet()) {
            Daily daily = dailyarray.get(treeKey);
            if (isFirst) {
                lastPrice = daily.getCloseprice();
                returnSeries.add(treeKey, 0);
                isFirst = false;
            } else {
                returnSeries.add(treeKey, (daily.getCloseprice() - lastPrice) / lastPrice);
                lastPrice = daily.getCloseprice();
            }
        }
        return returnSeries;
    }

    public TimeSeries logReturnSeries() {
        double fLastPrice = 0;
        boolean isFirst = true;
        for (Date treeKey : dailyarray.keySet()) {
            Daily daily = dailyarray.get(treeKey);
            if (isFirst) {
                fLastPrice = daily.getCloseprice();
                logReturnSeries.add(treeKey, 0);
                isFirst = false;
            } else {
                logReturnSeries.add(treeKey, Math.log((daily.getCloseprice() - fLastPrice) / fLastPrice));
                fLastPrice = daily.getCloseprice();
            }
        }
        return logReturnSeries;
    }

    public TimeSeries volumeReturnSeries() {

        double value = 0;
        boolean isFirst = true;
        for (Date treeKey : dailyarray.keySet()) {
            Daily daily = dailyarray.get(treeKey);

            if (isFirst) {
                value = daily.getVolume();
                volumeReturnSeries.add(treeKey, 0);
                isFirst = false;
            } else {
                volumeReturnSeries.add(treeKey, ((daily.getVolume() - value) / value));
                value = daily.getVolume();
            }
        }
        return volumeReturnSeries;
    }

    public TimeSeries volumeLogReturnSeries() {

        double value = 0;
        boolean isFirst = true;
        for (Date treeKey : dailyarray.keySet()) {
            Daily daily = dailyarray.get(treeKey);

            if (isFirst) {
                value = daily.getVolume();
                volumeLogReturnSeries.add(treeKey, 0);
                isFirst = false;
            } else {
                volumeLogReturnSeries.add(treeKey, Math.log((daily.getVolume() - value) / value));
                value = daily.getVolume();
            }
        }
        return volumeLogReturnSeries;

    }

    public double expectedReturn() {
        return getExpectedReturn(RETURN);
    }

    public double getExpectedReturn(PriceEnum option) {
        double Return = 0;
        switch (option) {
            case RETURN:
                Return = returnSeries.getMean();
                break;

            case LOGRETURN:
                Return = logReturnSeries.getMean();
                break;
        }
        return Return;
    }

    public double variance() {
        return getVariance(RETURN);
    }

    public double getVariance(PriceEnum option) {
        double Variance = 0;
        switch (option) {
            case RETURN:
                Variance = returnSeries.getVariance();
                break;
            case LOGRETURN:
                Variance = logReturnSeries.getVariance();
                break;
        }
        return Variance;
    }

    public double standardDeviation() {
        return getStandardDeviation(RETURN);
    }

    public double getStandardDeviation(PriceEnum option) {
        return Math.sqrt(getVariance(option));
    }

    public double annualExpectedReturn() {
        return (Math.pow(expectedReturn(), 365) - 1);
    }

    public double annualVariance() {
        return variance() * 365.0;
    }

    public double annualStandardDeviation() {
        return standardDeviation() * Math.sqrt(365.0);
    }

    public Date firstDay() {
        return firstDailyDate();
    }

    public Date lastDay() {
        return lastDailyDate();
    }

    public int getNDaily() {
        return length();
    }

    public Date getPrevDate(Date date) {
        return prevDate(date);
    }

    public Date getNextDate(Date date) {
        return nextDate(date);
    }

    public Daily getPrevDaily(Date date) {
        return prevDaily(date);
    }

    public Date firstDate() {
        return firstDailyDate();
    }

    public Date lastDate() {
        return lastDailyDate();
    }

    public Daily daily(Date date) {
        return dailyarray.get(date);
    }

    public boolean dataAvailable(Date date) {
        Daily daily = dailyarray.get(date);
        if (daily == null) return false;
        return true;
    }

    public Date prevDate(Date date) {
        return dailyarray.prevDate(date);
    }

    public Date nextDate(Date date) {
        return dailyarray.nextDate(date);
    }

    public Daily prevDaily(Date date) {
        Date prevDate = prevDate(date);
        if (prevDate == null)
            return null;
        else
            return daily(prevDate);
    }

    public double calculateReturn(Date date) {
        if (date == firstDate()) return 1;
        if (date == null) date = lastDate();
        Daily daily = daily(date);
        double Price = daily.price();
        Daily prevDaily = this.prevDaily(date);
        if (prevDaily != null)
            return Price / prevDaily.price();
        else
            return 1;
    }

    public double logReturn() {
        return logReturn(null);
    }

    public double logReturn(Date date) {
        return Math.log(calculateReturn(date));
    }

    public double re(Date date) {
        return calculateReturn(date);
    }

    public double price(Date date) {
        return price(date, TYPICALPRICE);
    }

    public double price(Date date, PriceEnum Option) {
        return value(date, Option);
    }

    public double low(Date date) {
        return dailyarray.get(date).getLow();
    }

    public double high(Date date) {
        return dailyarray.get(date).getHigh();
    }

    public double open(Date date) {
        return dailyarray.get(date).getOpenprice();
    }

    public double close(Date date) {
        return dailyarray.get(date).getCloseprice();
    }

    public int volume(Date date) {
        return dailyarray.get(date).getVolume();
    }

    public int openInterest(Date date) {
        return dailyarray.get(date).getOpenInterest();
    }

    public TimeSeries timeSeries(PriceEnum priceType, Date firstDate, Date lastDate) {
        TimeSeries series = new TimeSeries();

        GenericTimeSeries<Daily> dailyarray = this.dailyarray;
        dailyarray.forEach((date, daily) -> {
            double v = daily.price(priceType);
            series.add(daily.getDailydate(), v);
        });

        return series;
    }

    public TimeSeries buildHighSeries() {
        return timeSeries(HIGH, null, null);
    }

    public TimeSeries buildLowSeries() {
        return timeSeries(LOW, null, null);
    }

    public TimeSeries buildOpenSeries() {
        return timeSeries(OPEN, null, null);
    }

    public TimeSeries buildCloseSeries() {
        return timeSeries(CLOSE, null, null);
    }

    public TimeSeries buildLogAverageSeries() {
        return timeSeries(LOGAVERAGEPRICE, null, null);
    }

    public TimeSeries buildVolumeSeries() {
        return timeSeries(VOLUME, null, null);
    }

    public boolean dailyData(Date date) {
        if (dailyarray == null) {
            return false;
        }
        if (date == null) {
            return dailyarray.size() != 0;
        }
        return dataAvailable(date);
    }

    public int length() {
        return dailyarray.size();
    }

    public Date firstDailyDate() {
        return dailyarray.firstDate();
    }

    public Date lastDailyDate() {
        return dailyarray.lastDate();
    }

    public double getSpot() {
        return spot;
    }

    /**
     * Set spot value
     *
     * @param spot value
     */
    public void setSpot(double spot) {
        this.spot = spot;
        isSpotFixed = true;
    }

    public String getName() {
        return name;
    }

}
