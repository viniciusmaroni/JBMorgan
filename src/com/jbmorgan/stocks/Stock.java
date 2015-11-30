package com.jbmorgan.stocks;

/*
 * Stock
 * POJO (Java Bean) for represent a stock 
 * 
 */
public class Stock {

	// Stock symbol (ex: TEA)
	private String stockSymbol;
	
	// Type of stock (Common / Preferred)
	private StockType type;
	
	// Last dividend value
	private double lastDividend;
	
	// For preferred stocks % of fixed dividend
	private double fixedDividend;
	
	/// Par value of stock
	private double parValue;
	
	public Stock() {
	}

	public Stock(String stockSymbol, StockType type, double lastDividend, double fixedDividend, double parValue) {
		super();
		this.stockSymbol = stockSymbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public StockType getType() {
		return type;
	}

	public void setType(StockType type) {
		this.type = type;
	}

	public double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public double getParValue() {
		return parValue;
	}

	public void setParValue(double parValue) {
		this.parValue = parValue;
	}

	public double getDividendYield(double price) {
		// Prevines zero division
		if (price == 0)
			return 0;
		
		// Rule for common stock
		if (type == StockType.COMMON)
			return (lastDividend / price);			
		else
			return ((fixedDividend * parValue / 100) / price);			
	}
	
	public double getPERatio(double price) {
		
		// Take the dividend yeld
		double dividend = getDividendYield(price);
		
		// Prevines zero division
		if (dividend == 0)
			return 0;
		
		// If not zero divide, calculate P/E Ratio
		return (price / dividend);
	}

	@Override
	public String toString() {
		return "STOCK Symbol=" + stockSymbol + ", type=" + type + ", lastDividend=" + lastDividend
				+ ", fixedDividend=" + fixedDividend + ", parValue=" + parValue;
	}
	
}
