package com.jbmorgan.trades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import com.jbmorgan.stocks.Stock;
import com.jbmorgan.stocks.StocksTable;

/*
 * TradeRecords
 * 
 * Store trade stocks operations
 * 
 */
public class TradesRecord {

	private ArrayList<Trade> tradesRecordTable = null;
	
	// SingleTown design pattern
	private static TradesRecord tradesRecord;
	
	// SingleTown design pattern
	private TradesRecord() {
		tradesRecordTable = new ArrayList<Trade>();
	}
	
	// SingleTown design pattern
	public static TradesRecord getInstance() {
		if (tradesRecord == null)
			tradesRecord = new TradesRecord();
		
		return tradesRecord;
	}

	public void add(Trade trade) throws TradeException {
		// Is a valid trade?		
		if (trade.getDate() == null)
			throw new TradeException("Trade date must be informed!");
		if (trade.getPrice() <= 0)
			throw new TradeException("Trade price must be greater than zero!");		
		if (trade.getQuantity() <= 0)
			throw new TradeException("Trade quantity must be greater than zero!");

		tradesRecordTable.add(trade);
		Collections.sort(tradesRecordTable);
	}

	
	public double calculateVolumeWeightedStockPrice(String stockSymbol, int lastMinutes) {
		// Get end date
		Calendar startDate = Calendar.getInstance();
		startDate.add(Calendar.MINUTE, lastMinutes*-1);
				
		double totalTradedPrice = 0, totalQuantity = 0;

		// Acumulate quantity and price
		for (Trade trade : tradesRecordTable) {
			// Is the stock requested?
			if (!trade.getStock().getStockSymbol().equals(stockSymbol))
				continue;
			
			// Check date range of trade
			if (lastMinutes > 0 && trade.getDate().before(startDate.getTime()))
				continue;

			totalTradedPrice+=(trade.getPrice() * trade.getQuantity());
			totalQuantity+=trade.getQuantity();
		}
		
		// Prevent zero divide
		if (totalQuantity == 0)
			return 0;
		
		return totalTradedPrice / totalQuantity;
	}

	public double calculateVolumeWeightedStockPrice(String stockSymbol) {
		// Calculate the time range 
		return calculateVolumeWeightedStockPrice(stockSymbol, 0);
	}
	
	// Calculate all share index for a stock
	public double calculateGBCEAllShareIndex() {
		StocksTable stocksTable = StocksTable.getInstance();
		
		// Iterate all stocks and multiply to Geometric Mean
		double sumOfPrices = 1;
		int quantityOfStocks = 0;
		
		for (Stock stock : stocksTable.getStocks()) {
			double vwsp = calculateVolumeWeightedStockPrice(stock.getStockSymbol());
			sumOfPrices = sumOfPrices * vwsp;
			quantityOfStocks++;
		}
		
		// If nothing to do
		if (quantityOfStocks < 2)
			return 0;
		
		return Math.pow(sumOfPrices, 1.0/quantityOfStocks);
	}

}
