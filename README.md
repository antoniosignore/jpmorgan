# jpmorgan test


GitHub has plenty of project that provide a resolution for the this exercise. Just execute in Google the following search

    github super simple stock

and you get a lot of project.

I did go through some of them and went my own way with the exception of taking the precooked formula and the 
StockPredicate (which I found nice) from :

    https://github.com/jainebri/Super-Simple-Stocks   <--- (Credits)
    
To be honest it is more confusing than helpful..not being the formula clearly the complexity so I tried a different approach:.

    model the problem like a financial firm would do it:

You have a portofolio which is a basket of financial instruments (for this exercise only the asset class Stock)
 
The instruments are populated with quotes (Daily.java) in date and time.

Adding trades to a portfolio will add/remove items in the portoflio.

So I created the 5 stocks, created few trades and added the trades in the portfolio.

The resulting portfolio had then 5 stocks positions in it and few quotes in it.

That demonstrates that I can hold trades (the portfolio is the basket) which was one of the requirements.

Then I calculated the 2 measure per stock (yield and pe ratio), the 2 measures per positions in the portfolio.
 
It should work...of course more testing is required.
 
Test coverage is not terrible considering that I haev taken some precooked classes from an old groovy project 
I worked few years ago when I was trying to build trading systems. Lots of room for improvement there...

The relevant specific Unit Test case is the class:

    src/test/com/jpmorgan/test/StockTest







 
