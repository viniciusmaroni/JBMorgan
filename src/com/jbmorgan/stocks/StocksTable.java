package com.jbmorgan.stocks;

import java.util.Collection;
import java.util.HashMap;
/*
 * StockTable
 * 
 * Store stocks in memory
 */
public class StocksTable {

	// Store stocks
	private HashMap<String, Stock> stocks;
	
	// Implements SingleTow
	private static StocksTable stocksTable = null;
	
	private StocksTable() {		
		stocks = new HashMap<String, Stock>();
	}
	
	// Singletown design pattern
	public static StocksTable getInstance() {
		// Implments singletow design pattern
		if (stocksTable == null)
			stocksTable = new StocksTable();

		return stocksTable;
	}

	// Add a new stock in list
	public void add(Stock stock) throws StockException {

		// Is a stock valid?
		if (stock.getStockSymbol() == null || stock.getStockSymbol().equals(""))
			throw new StockException("Stock symbol must be informed!");
		if (stock.getType() == null)
			throw new StockException("Stock type must be informed!");
		if (stock.getFixedDividend() < 0)
			throw new StockException("Stock fixed dividend must be a positive number!");
		if (stock.getLastDividend() < 0)
			throw new StockException("Stock last dividend must be a positive number!");
		if (stock.getParValue() < 0)
			throw new StockException("Stock par value must be a positive number!");
	
		// Ok, put in array
		stocks.put(stock.getStockSymbol(), stock);
	}
	
	// Remove a stock from list
	public void remove(String stockSymbol) {
		if (stocks.containsKey(stockSymbol))
			stocks.remove(stockSymbol);
	}
	
	// Find a stock by symbol
	public Stock getStockBySymbol(String stockSymbol) {
		return stocks.get(stockSymbol);
	}
	
	// Return stocks list
	public Collection<Stock> getStocks() {
		return stocks.values();
	}
	
}
