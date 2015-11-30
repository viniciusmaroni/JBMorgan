package com.jbmorgan.trades;

import java.util.Date;

import com.jbmorgan.stocks.Stock;

/*
 * Trade
 * 
 * Represents one trade of buy or sell stocks
 * 
 */
public class Trade implements Comparable<Trade> {

	private Date date;
	private Stock stock;
	private double quantity;
	private TradeType type;
	private double price;
	
	public Trade() {
	}

	public Trade(Date date, Stock stock, double quantity, TradeType type, double price) {
		super();
		this.date = date;
		this.stock = stock;
		this.quantity = quantity;
		this.type = type;
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public TradeType getType() {
		return type;
	}

	public void setType(TradeType type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int compareTo(Trade otherTrade) {
		// Compares first by stock symbol
		int stockEqual = this.getStock().getStockSymbol().compareTo(otherTrade.getStock().getStockSymbol());
		if (stockEqual != 0)
			return stockEqual;
		
		// If is the same stock compare by date
		int dateEqual = this.getDate().compareTo(otherTrade.getDate());
		
		return dateEqual;
	}
	
}
