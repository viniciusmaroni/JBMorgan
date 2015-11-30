package com.jbmorgan.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.jbmorgan.stocks.Stock;
import com.jbmorgan.stocks.StockException;
import com.jbmorgan.stocks.StockType;
import com.jbmorgan.stocks.StocksTable;
import com.jbmorgan.trades.Trade;
import com.jbmorgan.trades.TradeException;
import com.jbmorgan.trades.TradesRecord;
import com.jbmorgan.trades.TradeType;

/*
 * Test
 * JUnit test class
 */
public class TestApp  {

	// Data for test
	private StocksTable stocks = StocksTable.getInstance();
	private TradesRecord tradesRecord = TradesRecord.getInstance();
	private double INPUT_PRICE = 5.0;
	
	@Before
	public void runBeforeTest() {
		// Populate tables for tests
		try {
			stocks.add(new Stock("TEA", StockType.COMMON, 0, 0, 100));
			stocks.add(new Stock("POP", StockType.COMMON, 8, 0, 100));
			stocks.add(new Stock("ALE", StockType.COMMON, 23, 0, 60));
			stocks.add(new Stock("GIN", StockType.PREFERRED, 8, 2, 100));
			stocks.add(new Stock("JOE", StockType.COMMON, 13, 0, 250));			
		} catch(StockException e) {
			assertFalse(true);
		}
		
		// Populate tables for tests
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

		} catch (TradeException e) {
			assertFalse(true);
		} catch (ParseException e) {
			assertFalse(true);
		}		
	}
	
	@Test
	public void calculateDividentYeld() {
		try {
			// Exercise a: For a given stock (populate stocks)
			for (Stock stock : stocks.getStocks()) {
				double dividendYeld = stock.getDividendYield(INPUT_PRICE);
				assertTrue(dividendYeld >= 0);
			}		
		} catch (Exception e) {
			assertFalse(true);
		}
	}

	@Test
	public void calculatePERatio() {
		try {
			// Exercise a: For a given stock (populate stocks)
			for (Stock stock : stocks.getStocks()) {
				double peRatio = stock.getPERatio(INPUT_PRICE);
				assertTrue(peRatio >= 0);
			}		
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void calculateVolumeWeightedStockPrice() {
		double vwsp =  tradesRecord.calculateVolumeWeightedStockPrice("TEA", 15);
		assertTrue(vwsp >= 0);
	}
	
	@Test
	public void calculateGBCEAllShareIndex() {
		double gbce = tradesRecord.calculateGBCEAllShareIndex();
		assertTrue(gbce > 0);
	}
}
