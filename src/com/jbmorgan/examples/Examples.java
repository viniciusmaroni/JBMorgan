package com.jbmorgan.examples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jbmorgan.stocks.Stock;
import com.jbmorgan.stocks.StockException;
import com.jbmorgan.stocks.StockType;
import com.jbmorgan.stocks.StocksTable;
import com.jbmorgan.trades.Trade;
import com.jbmorgan.trades.TradeException;
import com.jbmorgan.trades.TradeType;
import com.jbmorgan.trades.TradesRecord;

/*
 * Examples
 * 
 * Class made for a sample exercise requested
 * 
 */
public class Examples {

	public static void main(String[] args) {
		
		// Exercise a: For a given stock (populate stocks)
		StocksTable stocks = StocksTable.getInstance();
		try {
			stocks.add(new Stock("TEA", StockType.COMMON, 0, 0, 100));
			stocks.add(new Stock("POP", StockType.COMMON, 8, 0, 100));
			stocks.add(new Stock("ALE", StockType.COMMON, 23, 0, 60));
			stocks.add(new Stock("GIN", StockType.PREFERRED, 8, 2, 100));
			stocks.add(new Stock("JOE", StockType.COMMON, 13, 0, 250));			
		} catch(StockException e) {
			System.out.println("Error to add stock " + e.getMessage());
			System.exit(-1);
		}
		
		// Exercise a.i and a.ii: Given a price as input, calculate the dividend yeld e P/E ratio.
		final double INPUT_PRICE = 5;
		System.out.println(">>>>> Exercise a.i and a.ii: (Dividend Yeld and P/E Ratio) for a " + INPUT_PRICE + " input value: ");
		System.out.println("----------------------------------------------------------------------------------------- ");
		for (Stock stock : stocks.getStocks()) {
			System.out.println(stock);
			System.out.println("Dividend Yeld: " + stock.getDividendYield(INPUT_PRICE));
			System.out.println("P/E Ratio " + stock.getPERatio(INPUT_PRICE));
			System.out.println("----------------------------------------------------------------------------------------- ");
		}
		
		// Exercise a.iii record trades
		TradesRecord tradesRecord = TradesRecord.getInstance();
		try {
			SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			tradesRecord.add(new Trade(date.parse("06/09/2015 11:00"), stocks.getStockBySymbol("POP"), 200, TradeType.BUY, 4.00d));
			tradesRecord.add(new Trade(date.parse("10/11/2015 12:00"), stocks.getStockBySymbol("POP"), 50, TradeType.BUY, 4.50d));

			tradesRecord.add(new Trade(date.parse("07/11/2015 13:00"), stocks.getStockBySymbol("TEA"), 120, TradeType.BUY, 3.00d));
			tradesRecord.add(new Trade(date.parse("08/11/2015 17:00"), stocks.getStockBySymbol("TEA"), 180, TradeType.BUY, 2.60d));

			tradesRecord.add(new Trade(date.parse("10/10/2015 08:00"), stocks.getStockBySymbol("ALE"), 100, TradeType.BUY, 5.00d));
			tradesRecord.add(new Trade(date.parse("15/10/2015 09:00"), stocks.getStockBySymbol("ALE"), 120, TradeType.BUY, 5.12d));
			tradesRecord.add(new Trade(date.parse("18/11/2015 11:00"), stocks.getStockBySymbol("ALE"), 200, TradeType.BUY, 5.14d));
			tradesRecord.add(new Trade(new Date(), stocks.getStockBySymbol("ALE"), 100, TradeType.SELL, 5.15d));

			tradesRecord.add(new Trade(date.parse("20/11/2015 16:00"), stocks.getStockBySymbol("GIN"), 100, TradeType.BUY, 3.00d));
			tradesRecord.add(new Trade(date.parse("19/11/2015 15:10"), stocks.getStockBySymbol("GIN"), 100, TradeType.BUY, 3.00d));
			tradesRecord.add(new Trade(date.parse("22/11/2015 14:20"), stocks.getStockBySymbol("GIN"), 100, TradeType.SELL, 3.10d));

			tradesRecord.add(new Trade(date.parse("22/11/2015 08:20"), stocks.getStockBySymbol("JOE"), 200, TradeType.BUY, 4.00d));
			tradesRecord.add(new Trade(new Date(), stocks.getStockBySymbol("JOE"), 200, TradeType.BUY, 4.00d));
			tradesRecord.add(new Trade(date.parse("22/11/2015 08:40"), stocks.getStockBySymbol("JOE"), 200, TradeType.BUY, 4.00d));
			
			System.out.println("VWSP in last 15 minutes: " + tradesRecord.calculateVolumeWeightedStockPrice("ALE", 15));
			
		} catch (TradeException e) {
			System.out.println("Error to add trade: " + e.getMessage());
			System.exit(-2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
		// Exercise b - geometric mean
		System.out.println("----------------------------------------------------------------------------------------- ");
		System.out.println("GBCE All Share Index: " + tradesRecord.calculateGBCEAllShareIndex());
	}

}
