package com.jpmorgan.enums;

public enum PriceEnum {

    asset,
    derivative,

    INTERPOLATED,

    FUNDAMENTAL,
    QUANTITATIVE,
    TECHNICAL,
    SHORT,
    LONG,

    // ETradeTransaction

    // EOrderOption
    MARKET,
    LIMIT,
    STOP,
    STOPLIMIT,
    MARKETONOPEN,
    MARKETONCLOSE,

    kNewOrder,
    kTransmitted,
    kAcknowledged,
    kAccepted,
    kPartiallyFilled,
    kFilled,
    kCancelled,
    kRejected,

    kDayOrder,
    kEXT,                // extended hours
    kGTT,                // good till time
    kGTS,                // good till second
    kGTC,                // good till cancelled
    kGTX,                // good till crossing session
    kFOK,                 // padright or kill

    // EInvestOption
    kInvestOnDate,
    kInvestOnBuy,

    // ETickBase
//    static final long MILLISECONDS_DAY xcL,
//    static final long SECONDS_DAY      ,
    SECOND,
    MINUTE,
    HOUR,
    DAY,
    WEEK,
    MONTH,
    YEAR,

    ALL,
    OBJECT,
    DATA,
    DAILY,
    TICK,
    TRADE,
    QUOTE,
    BAR,

    PRICE,
    TRADEPRICE,
    MEDIANPRICE,
    TYPICALPRICE,
    WEIGHTEDPRICE,
    AVERAGEPRICE,
    LOGAVERAGEPRICE,
    RETURN,
    LOGRETURN,
    VOLUMERETURN,
    VOLUMERETURNLOG,
    HIGH,
    LOW,
    OPEN,
    CLOSE,
    VOLUME,


    SPREAD,
    SIZE,
    BIDSIZE,
    ASKSIZE,
    TRADESIZE,

    kDailyExact,
    kDailyPercent,
    kAnnualExact,
    kAnnualPercent,

    kVObject,
    kInstrument,
    kAsset,
    kStock,
    kBond,
    kFund,
    kIndex,
    kFixedIncome,
    kBill,
    kNote,
    kPortfolio,
    kDerivative,
    kFutures,
    kOption,
    kCurrency,
    kRate,
    kCompany,
    kCalendar,
    kExchange,
    kBankAccount,

    // EBondType
    kConvertibleBond,
    kPutableBond,
    kCallableBond,
    kZeroCouponBond,

    // EOptionType
    kCall,
    kPut,
    kEuropean,
    kEuropeanPut,
    kAmerican,
    kAmericanPut,
    kBermudian,
    kDigital,

    // EOptionPosition
    kheMoney,
    kAtTheMoney,
    kOutOfTheMoney,

    // EOptionPrice
    kBinomial,
    kMonteCarlo,
    kBlackScholes,

    // EPortfolioOption
    kFixedWeight,
    kFixedAmount,
    kFixedWealth,

    // ESortOption
    kUnsorted,
    kSortByName,
    kSortByAmount,
    kSortByReturn,
    kSortByVolatility,
    kSortByWeight,
    kSortByPosition,
    kSortByInstrument,

    // ETickerOption
    kDefault,
    kBloomberg,
    kReuters,
    kBridge,
    kNYSE,
    kISIN,
    kCUSIP,
    kSEDOL,
    kSICOVAM,
    kYahoo,
    kTlx,

    // EMonitorOption
    kPlayBack,
    kMaxSpeed,
    kRealTime,
    kMonitorId,
    kMonitorRequestId,
    kHeaderRequestId,

    // ESpreadOption
    kBidAsk,
    kAskAsk,
    kBidBid,

    kCompact,
    kLarge,

    kGetFirstIndex,

    kGetLastIndex,
    kGetFirstDailyDate,
    kGetLastDailyDate,
    kGetIndexByDate,
    kGetDailyByDate,
    kGetDailyByIndex,
    kGetSeries,
    kGetHighSeries,
    kGetLowSeries,
    kGetOpenSeries,
    kGetCloseSeries,
    kGetVolumeSeries,
    kGetDailySeries,
    kGetQuoteSeries,
    kGetTradeSeries,
    kGetDailyArray,
    kGetQuoteArray,
    kGetTradeArray,

    VALID,
    NOTAVAILABLE,
    ERPOLATED,

    um_Exact,
    um_OnLoad,
    um_Minute,
    um_Hour,
    um_Day,

    kPercentDividend,
    kCashDividend,
    kContinuousDividend,
    kGrowthRateDividend,

    k,
    kFloat,

    kOptimizer,
    kCoordinateDescent,
    kGeneticAlgorithm,
    kSimulatedAnnealing,

    kEfficientFrontier,
    kPerceptron,

    kQuiet,
    kVerbose,
    kDebug,
    kOverwrite,

    kPercent,
    kFixed,
    kMixed,
    kComplete,

    kObjectiveCalls,
    kErrorTolerance,
    kStopTemperature,
    kAcceptedUphills,
    kAcceptedDownhills,

    kNormalGraph,
    kTitleGraph,
    kIndicatorGraph,
    kTrendGraph,
    kVolumeGraph,
    kOscillatorGraph,
    kSignalGraph,
    kPortfolioGraph,

    kNoRange,
    kDateRange,
    kDayRange,

    Jan,
    Feb,
    Mar,
    Apr,
    May,
    Jun,
    Jul,
    Aug,
    Sep,
    Oct,
    Nov,
    Dec,

    Mon,
    Tue,
    Wed,
    Thu,
    Fri,
    Sat,
    Sun,
    reformYear,
    reformDayNumber,
    reformmonth,
    beginDSTDay,
    beginDSTmonth,
    endDSTDay,
    endDSTmonth,

    PAYOFF_EUROPEAN_CALL,
    PAYOFF_EUROPEAN_PUT,
    PAYOFF_ARITHMETRIC_AVERAGE,
    PAYOFF_GEOMETRIC_AVERAGE,
    PAYOFF_MAX,
    PAYOFF_MIN,

    PRECEDING,
    FOLLOWING,
    MODPRECEDING,
    MODFOLLOWING,
    NOROLL,


    TREND,
    kEnvelope,
    OSCILLATOR,
    kVolumeIndicator,

    BID,
    ASK,
    MID,


    DOWN_AND_OUT,
    DOWN_AND_IN,
    UP_AND_OUT,
    UP_AND_IN,
    ARREARS_PAY_MODE,
    ADVANCE_PAY_MODE,
    ADV_DISC_PAY_MODE,

    FIXED,
    FLOAT,
    REVERSE_FLOAT,

    GENERATE_FROM_START,
    GENERATE_FROM_END,
    GENERATE_FRN,

    // options
    nUndType_Stock,
    nUndType_Forex,

    nOptType_None,
    nOptType_Call,

    nOptType_Put,
    nOptType_Fut,
    nOptType_Und,

    nOptStyle_None,
    nOptStyle_Europ,
    nOptStyle_Amer,

    szOptType_Call,
    szOptType_Put,
    szOptType_Fut,
    szOptType_Und,
    szOptType_None,
    szOptStyle_Europ,
    szOptStyle_Amer,

    nGraphType_Price,
    nGraphType_PL,
    nGraphType_Delta,
    nGraphType_Gamma,
    nGraphType_Theta,
    nGraphType_Vega,
    nGraphType_Rho,

    ACCURACY,
    PI,
    ERROR,

    BETA_TURNING_PO,
    GAMMA_TURNING_PO,
    GEOMETRIC_TURNING_PO,

    YCASK,
    YCMID,
    YCBID,

    numDR,

    MONTHS,
    MILLISECONDS,
    SECONDS,
    MINUTES,
    HOURS,

    NO_MOVE,
    PARALLEL,
    PROPORTIONAL,
    TWIST,
    BRIDGE,
    INVERSE,

    FR_DAILY,
    // different year lengths per different conventions
//    [] YearLengths  {, , , , , , },

    EUROPEAN_CALL,
    EUROPEAN_PUT,
    AMERICAN_CALL_BS,
    AMERICAN_PUT_BS,
    AMERICAN_CALL_BAW,
    AMERICAN_PUT_BAW,
    Down_Knock_in_call,
    Up_Knock_in_call,
    Down_Knock_in_put,
    Up_Knock_in_put,
    Down_Knock_out_call,
    Up_Knock_out_call,
    Down_Knock_out_put,
    Up_Knock_out_put,

    BlackScholes,
    Binomial,
    Montecarlo,

//    SimpleMovingAverage,
//    WeightedMovingAverage,
//    SingularSpectrumFirstComponent,
//    SingularSpectrumPrediction,
//    PriceChannelUpper,
//    PriceChannelLower,
//    UpperBollingerBand,
//    LowerBollingerBand,
//    kVHF,
//    SimpleMovingVariance,
//    SimpleMovingDivergence,
//    SimpleMovingCovariance,
//    Momentum,
//    To,
//    MACD,
//    RateOfChange,
//    RelativeStrengthIndex,
//    RelativeStrengthIndex2,
//    CommoditiChannelIndicator,
//    kKRI,
//    Oscillator,
//    PriceChannelR,
//    FI,
//    D,
//    K,
//    kVA,
//    kVAI,
//    kCHO,
//    TrueChange,      // TR   True Range
//    AverageTrueChange,     // ATR  Average True Range
//    kUserDefined,
//    MoneyFlowOverPeriod,
//    AccumulateDistributionOverPeriod,
//    ChaikinOscillatorOverPeriod,
//    ChaikinMoneyFlowOverPeriod,
//    AroonOscillatorOverPeriod,
//    AroonDownOverPeriod,
//    AroonUpOverPeriod,
//    TrueRangePeriod,
//    kFastStochasticPeriod,
//    dStochastic,
//    DStochasticSmoothed,
//    ChaikinVolatility,
//    MACDSignal,
//    PlusDirectionalMovementPeriod,
//    RateOfChangePeriod,
//    PriceActionOverPeriod,
//    BalanceOfPowerOverPeriod,
//    MarketFacilitationIndexOverPeriod,
//    CommodityChannelIndexOverPeriod,
//    MomentumPctPeriod,
//    GeometricMovingAverage,
//    LinearlyWeightedMovingAverage,
//    TriangularMovingAverage,
//    ExponentiallyWeightedMovingAverage,
//    Kairi,
//    RateOfChangePeriod;

}