package com.jbmorgan.trades;

/*
 * TradeException
 * 
 * Represents a exception on trade
 * 
 */
public class TradeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3970152314060858097L;

	public TradeException() {
	}

	public TradeException(String message) {
		super(message);
	}

	public TradeException(Throwable cause) {
		super(cause);
	}

	public TradeException(String message, Throwable cause) {
		super(message, cause);
	}

	public TradeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
