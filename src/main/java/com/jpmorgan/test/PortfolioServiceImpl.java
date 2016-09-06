package com.jpmorgan.test;


import com.jpmorgan.beans.Daily;
import com.jpmorgan.beans.Trade;
import com.jpmorgan.enums.TradeEnum;
import com.jpmorgan.model.Asset;
import com.jpmorgan.model.Instrument;
import com.jpmorgan.model.Portfolio;
import com.jpmorgan.model.PortfolioEntry;
import com.jpmorgan.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    public PortfolioServiceImpl() {
    }

    void clear(Portfolio portfolio) {
        portfolio.getItems().clear();
        portfolio.getTrades().clear();
    }

    boolean isEmpty(Portfolio portfolio) {
        if (portfolio.getItems().size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Date firstDay(Portfolio portfolio) {
        if (portfolio.getFirstDate() != null) {
            return portfolio.getFirstDate();
        }
        portfolio.getItems().forEach(it -> {
                    Instrument instrument = it.getInstrument();
                    if (instrument instanceof Asset) {
                        if (instrument.firstDay() != null) {
                            portfolio.setFirstDate(DateUtils.max(portfolio.getFirstDate(), instrument.firstDay()));
                        }
                    }
                }
        );
        return portfolio.getFirstDate();
    }

    @Override
    public Date latestDay(Portfolio portfolio) {
        if (portfolio.getLastDate() != null) {
            return portfolio.getLastDate();
        }

        portfolio.getItems().forEach(it -> {
                    Instrument instrument = it.getInstrument();
                    if (instrument.lastDay() != null) {
                        portfolio.setLastDate(DateUtils.min(portfolio.getLastDate(),
                                instrument.lastDay()));
                    }
                }
        );
        return portfolio.getLastDate();
    }

    @Override
    public PortfolioEntry entry(Portfolio portfolio, Instrument instrument) {
        if (instrument == null) throw new IllegalArgumentException("instrument cannot be null");
        final PortfolioEntry[] entry = new PortfolioEntry[1];
        if (portfolio.getItems() != null)

            portfolio.getItems().forEach(it -> {
                if (it.getInstrument().getName().equalsIgnoreCase(instrument.getName())) {
                    entry[0] = it;
                }
            });
        return entry[0];
    }

    @Override
    public void add(Portfolio portfolio, PortfolioEntry item) {

        if (entry(portfolio, item.getInstrument()) != null) {
            System.out.println("addEntry. Instrument: " + item.getInstrument().getName() + " already exists in portfolio " + portfolio.getName());
            return;
        }

        if (item.getInstrument() instanceof Asset)
            portfolio.setRangeBounds(item.getInstrument().getLowerRangeDate(), item.getInstrument().getUpperRangeDate());

        portfolio.getItems().add(item);
    }


    @Override
    public void add(Portfolio portfolio, Instrument instrument) {
        if (instrument == null) throw new IllegalArgumentException("instrument cannot be null");
        add(portfolio, instrument, 0);
    }

    @Override
    public void add(Portfolio portfolio, Instrument instrument, int Amount) {
        PortfolioEntry item = new PortfolioEntry(instrument, Amount, portfolio);
        add(portfolio, item);
    }

    @Override
    public void add(Portfolio portfolio, Trade trade) {

        Instrument instrument = trade.getInstrument();

        PortfolioEntry entry = entry(portfolio, instrument);

        if (entry == null) {
            entry = new PortfolioEntry(instrument, portfolio);
            if (trade.getTradeAction() == TradeEnum.BUY) {
                entry.setAmount(trade.getAmount());
            } else if (trade.getTradeAction() == TradeEnum.SELL) {
                System.out.println("addTransaction. No long position on sell for " + trade.getInstrument().getName() + " in " + portfolio.getName());
                return;
            } else if (trade.getTradeAction() == TradeEnum.SELLSHORT) {
                entry.setAmount(-trade.getAmount());
            } else if (trade.getTradeAction() == TradeEnum.BUYSHORT) {
                System.out.println("addTransaction. No short position on buy short for " + trade.getInstrument().getName() + " in " + portfolio.getName());
                return;
            }
            portfolio.getTrades().add(trade);
            add(portfolio, entry);
        } else {
            int amount = 0;
            if (trade.getTradeAction() == TradeEnum.BUY) {
                if (entry.getAmount() < 0) {
                    System.out.println("addTransaction. Short position on buy for " + trade.getInstrument().getName() + " in " + portfolio.getName());
                    return;
                }
                amount = entry.getAmount() + trade.getAmount();
            } else if (trade.getTradeAction() == TradeEnum.SELL) {
                amount = entry.getAmount() - trade.getAmount();
                if (amount < 0) {
                    System.out.println("addTransaction. Sell amount larger than long position for" + trade.getInstrument().getName() + " in " + portfolio.getName());
                    return;
                }
            } else if (trade.getTradeAction() == TradeEnum.SELLSHORT) {
                if (entry.getAmount() > 0) {
                    System.out.println("addTransaction. Long position in instrument on sell short: " + portfolio.getName());
                    return;
                }
                amount = entry.getAmount() - trade.getAmount();
            } else if (trade.getTradeAction() == TradeEnum.BUYSHORT) {
                if (entry.getAmount() > 0) {
                    System.out.println("addTransaction. Long position on buy short for " + trade.getInstrument().getName() + " in " + portfolio.getName());
                    return;
                }
                amount = entry.getAmount() + trade.getAmount();
                if (amount > 0) {
                    System.out.println("addTransaction. Buy short amount larger than short position: " + portfolio.getName());
                    return;
                }
            }
            portfolio.getTrades().add(trade);

            if (amount == 0) {
                remove(portfolio, instrument);
            } else {
                entry.setAmount(amount);
            }
        }
    }

    /*
    // add series of trade transactions
    public void add(Portfolio portfolio, TransactionSeries series) {
        if (series == null) throw new IllegalArgumentException("series cannot be null");
        int N = series.getN();
        for (int i = 0; i < N; i++) {
            add(portfolio, series.getTransaction(i));
        }
    }
    */

    @Override
    public void invest(Portfolio portfolio, double wealth) {
        invest(portfolio, wealth, null);
    }

    // Invest wealth into portfolio according to current portfolio weights
    @Override
    public void invest(Portfolio portfolio, double wealth, Date date) {

        if (portfolio.getItems() == null || portfolio.getItems().isEmpty())
            throw new RuntimeException("no instruments to invest money into");

        if (portfolio.getItems() != null)
            portfolio.getItems().forEach(entry -> {
                        double price = 0;
                        if (entry.getInstrument().isDataAvailable(date)) {
                            price = entry.getInstrument().getLast();
                        }
                        /*
                        else {
                            price = YahooUtils.getLastTradedValue(asset.name)
                        }*/
                        setAmount(portfolio, entry, (int) (getItemAmount(entry) + wealth * getWeight(portfolio, entry.getInstrument()) / price));
                    }
            );
    }

    @Override
    public Trade buy(Portfolio portfolio, Instrument instrument, int amount) {
        return buy(portfolio, instrument, amount, null);
    }

    @Override
    public Trade buy(Portfolio portfolio, Instrument instrument, int amount, Date date) {
        if (date == null) date = new Date();
        Trade transaction = new Trade(instrument, TradeEnum.BUY, amount, instrument.getPrice(date), date);
        add(portfolio, transaction);
        return transaction;
    }

    @Override
    public Trade sell(Portfolio portfolio, Instrument instrument, int amount) {
        return sell(portfolio, instrument, amount, new Date());
    }

    @Override
    public Trade sell(Portfolio portfolio, Instrument instrument, int amount, Date date) {
        if (date == null) date = new Date();
        Trade transaction = new Trade(instrument, TradeEnum.SELL, amount, instrument.getPrice(date), date);
        add(portfolio, transaction);
        return transaction;
    }

    @Override
    public Trade sellShort(Portfolio portfolio, Instrument instrument, int amount) {
        return sellShort(portfolio, instrument, amount, null);
    }

    @Override
    public Trade sellShort(Portfolio portfolio, Instrument instrument, int Amount, Date date) {
        if (date == null) date = new Date();
        Trade transaction = new Trade(instrument, TradeEnum.SELLSHORT, Amount, instrument.getPrice(date), date);
        add(portfolio, transaction);
        return transaction;
    }

    @Override
    public Trade buyShort(Portfolio portfolio, Instrument instrument, int Amount) {
        return buyShort(portfolio, instrument, Amount, new Date());
    }

    // Buy short
    @Override
    public Trade buyShort(Portfolio portfolio, Instrument instrument, int Amount, Date date) {
        if (date == null) date = new Date();
        Trade transaction = new Trade(instrument, TradeEnum.BUYSHORT, Amount, instrument.getPrice(date), date);
        add(portfolio, transaction);
        return transaction;
    }

    @Override
    public Trade sell(Portfolio portfolio, Instrument instrument) {
        return sell(portfolio, instrument, new Date());
    }

    // Sell everything - todo
    @Override
    public Trade sell(Portfolio portfolio, Instrument instrument, Date date) {
        int amount;
        PortfolioEntry entry = entry(portfolio, instrument);
        if (entry != null) {
            amount = entry.getAmount();
        } else {
            return null;
        }
        if (date == null) date = new Date();
        Trade transaction = new Trade(instrument, TradeEnum.SELL, amount, instrument.getPrice(date), date);
        add(portfolio, transaction);
        return transaction;
    }

    // delete instrument from portfolio
    @Override
    public void remove(Portfolio portfolio, Instrument instrument) {

        if (portfolio.getItems() != null)
            portfolio.getItems().forEach(entry -> {
                        if (entry.getInstrument().getName().equalsIgnoreCase(instrument.getName())) {
                            portfolio.getItems().remove(entry);
                        }
//                normalizeWeights(portfolio);
                    }
            );
    }

    // Return weight of this instrument in the portfolio
    // Return 0 if instrument is not in the portfolio
    @Override
    public double getWeight(Portfolio portfolio, Instrument instrument) {
        PortfolioEntry entry = entry(portfolio, instrument);
        if (entry != null) {
            return entry.getAmount();
        } else {
            return 0;
        }
    }

    // Return position of this instrument in the portfolio
    // Return 0 if instrument is not in the portfolio
    @Override
    public int position(Portfolio portfolio, Instrument instrument) {
        PortfolioEntry entry = entry(portfolio, instrument);
        if (entry != null) {
            return entry.position();
        } else {
            return 0;
        }
    }

    // Return amount of this instrument in the portfolio
    // Return 0 if instrument is not in the portfolio
    @Override
    public int amount(Portfolio portfolio, Instrument instrument) {
        PortfolioEntry entry = entry(portfolio, instrument);
        if (entry != null) {
            return entry.getAmount();
        } else {
            return 0;
        }
    }


    @Override
    public double amount(Portfolio portfolio) {
        // Return amount of all getItems() in portfolio
        final int[] Amount = {0};

        if (portfolio.getItems() != null)
            portfolio.getItems().forEach(it -> {
                        Amount[0] += it.getAmount();
                    }
            );
        return Amount[0];
    }


    // Return wealth for i-th asset in portfolio
    @Override
    public double wealth(Portfolio portfolio, Instrument asset, Date date) {
        double price = 0;
        if (asset.isDataAvailable(date)) {
            price = asset.premium();
        } /*else {
            price = YahooUtils.getLastTradedValue(asset.name)
        }*/
        return price * entry(portfolio, asset).getAmount();
    }


    @Override
    public double getWealth(Portfolio portfolio) {
        return getWealth(portfolio, null);
    }

    // Return wealth of portfolio
    @Override
    public double getWealth(Portfolio portfolio, Date date) {
        final double[] Wealth = {0};

        portfolio.getItems().forEach(it -> {
            Wealth[0] += wealth(portfolio, it.getInstrument(), date);
        });

        return Wealth[0];
    }

    // Normalize weigts of all stock in portfolio to keep
    // weight sum equal to unity and satisfy boundary conditions
    // Note that we exclude stock with zero weights from the portfolio,
    // meaning that such stock will have zero weight after normalization
  /*  public void normalizeWeights(Portfolio portfolio) {
        double WeightSum = 0;
        int i = 0;
        for (i = 0; i < portfolio.getItems().size(); i++) {
            WeightSum += getWeight(portfolio, portfolio.getItems().get(i).getInstrument());
        }
        for (i = 0; i < portfolio.getItems().size(); i++) {
            setWeight(portfolio, i, getWeight(portfolio, portfolio.getItems().get(i).getInstrument()) / WeightSum);
        }
        WeightSum = 1;
        boolean InBounds = true;
        for (i = 0; i < portfolio.getItems().size(); i++) {
            if (getWeight(portfolio, portfolio.getItems().get(i).getInstrument()) != 0) {
                if (getWeight(portfolio, portfolio.getItems().get(i).getInstrument()) < lowerBound(portfolio, portfolio.getItems().get(i).getInstrument())) {
                    InBounds = false;
                    break;
                }
            }
        }
        if (!InBounds) {
            double LowerBoundSum = 0;
            for (i = 0; i < portfolio.getItems().size(); i++) {
                if (getWeight(portfolio, portfolio.getItems().get(i).getInstrument()) != 0) {
                    LowerBoundSum += lowerBound(portfolio, portfolio.getItems().get(i).getInstrument());
                }
            }
            for (i = 0; i < portfolio.getItems().size(); i++) {
                if (getWeight(portfolio, portfolio.getItems().get(i).getInstrument()) != 0) {
                    setWeight(portfolio, portfolio.getItems().get(i).getInstrument(), lowerBound(portfolio, portfolio.getItems().get(i).getInstrument()) + getWeight(portfolio, portfolio.getItems().get(i).getInstrument()) * (1 - LowerBoundSum) / WeightSum);
                }
            }
        }
    }
*/
    /*
    public void normalize(Portfolio portfolio, PriceEnum Option) {
        if (Option == PriceEnum.kFixedWeight) {
            normalizeWeights(portfolio);
        } else {
            double Wealth = getWealth(portfolio);
            for (int i = 0; i < portfolio.getItems().size(); i++) {
                setWeight(portfolio, i, wealth(portfolio, i) / Wealth);
            }
        }
    }

*/

    // Check boundary conditions. Return true if feasible
    /*
    public boolean checkBounds(Portfolio portfolio) {
        double lowsum = 0;
        double upsum = 0;
        for (int i = 0; i < portfolio.getItems().size(); i++) {
            lowsum += lowerBound(portfolio, i);
            upsum += upperBound(portfolio, i);
            if (lowerBound(portfolio, i) >= upperBound(portfolio, i)) {
                System.out.println("CheckBounds LowerBound >= UpperBound for parameter " + i);
                return false;
            }
        }
        if (lowsum >= 1) {
            System.out.println("CheckBounds LowerBoundSum >= 1");
            return false;
        }
        if (upsum <= 1) {
            System.out.println("CheckBounds UpperBoundSum <= 1");
            return false;
        }
        return true;
    }
*/
    // Calculate models premium of portfolio
/*
    public double getModelPrice(Portfolio portfolio, String Model, String printMode) {
        final double[] price = {0};

        portfolio.getItems().forEach(it -> {
            price[0] += it.premium() * it.amount * it.position();
        });

        return price[0];
    }
*/
    // Return marked to market portfolio premium
    // If we consider a portfolio as one
    // financial instrument, its premium is
    // equal to its value

 /*   public double price(Portfolio portfolio, int Entry) {
        return m2m(portfolio, portfolio.getItems().get(Entry));
    }
*/

    // Return marked to market portfolio premium If we consider a portfolio as one
    // financial instrument, its premium is equal to its value
    @Override
    public double price(Portfolio portfolio, Date date) {
        return m2m(portfolio, date);
    }

    // Return portfolio premium If we consider a portfolio as one
    // financial instrument, its premium is equal to its value
    @Override
    public double premium(Portfolio portfolio) {
        return m2m(portfolio);
    }

    // Return marked to market portfolio value
//    public double m2m(Portfolio portfolio, int index) {
//        Instrument instrument;
//        Daily daily;
//        int amount;
//        double value = 0;
//        portfolio.getItems().each {
//            amount = it.amount
//            instrument = it.instrument
//            daily = instrument.getDaily();
//            if (!daily.valid()) {
//                daily = instrument.getPrevDaily(index);
//            }
//            if (daily != null) {
//                value += daily.getCloseprice() * amount;
//            } else {
//                System.out.println("getName. Out of data range :" + index);
//                return 0;
//            }
//        }
//        return value;
//    }

    /**
     * Mark 2 Market portfolio value
     *
     * @param date
     */
    @Override
    public double m2m(Portfolio portfolio, Date date) {
        if (date == null) {
            throw new IllegalArgumentException("date cannot be null");
        }
        final double[] value = {0};
        portfolio.getItems().forEach(it -> {
            int amount = it.getAmount();
            Instrument instrument = it.getInstrument();
            if (instrument instanceof Asset) {
                Daily daily = instrument.getDaily(date);
                if (daily == null) {
                    daily = instrument.getPrevDaily(date);
                }
                if (daily != null) {
                    value[0] += daily.getCloseprice() * amount;
                } else {
                    System.out.println("value. Out of data range");
                    value[0] = 0;
                }
            }
            /*
            if (instrument instanceof Derivative) {
                value += instrument.premium() * amount;
            }*/
        });
        return value[0];
    }

    // Return portfolio value
    @Override
    public double m2m(Portfolio portfolio) {
        final double[] Value = {0};
        portfolio.getItems().forEach(it -> {
            Value[0] += it.value();
        });
        return Value[0];
    }

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
    @Override
    public void setInstrument(Portfolio portfolio, PortfolioEntry entry, Instrument instrument) {
        entry.setInstrument(instrument);
    }

    @Override
    public void setAmount(Portfolio portfolio, PortfolioEntry entry, int amount) {
        entry.setAmount(amount);
    }


    @Override
    public void setWealth(Portfolio portfolio, double Wealth) {
        portfolio.setWealth(Wealth);
    }

    @Override
    public int nTransactions(Portfolio portfolio) {
        return portfolio.getTrades().size();
    }

    @Override
    public int nentries(Portfolio portfolio) {
        return portfolio.getItems().size();
    }

    @Override
    public int getItemAmount(PortfolioEntry item) {
        return item.getAmount();
    }

    @Override
    public List<PortfolioEntry> getInstruments(Portfolio portfolio) {
        return portfolio.getItems();
    }
}
