package com.jbmorgan.stocks;

/*
 * StockException
 * 
 * Represent exceptions for stock
 */
public class StockException extends Exception {

	private static final long serialVersionUID = 7449994158776695970L;

	public StockException() {
	}

	public StockException(String message) {
		super(message);
	}

	public StockException(Throwable cause) {
		super(cause);
	}

	public StockException(String message, Throwable cause) {
		super(message, cause);
	}

	public StockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
