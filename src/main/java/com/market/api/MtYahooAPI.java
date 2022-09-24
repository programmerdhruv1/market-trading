package com.market.api;

import java.io.IOException;
import java.util.Map;
import org.springframework.stereotype.Service;


import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class MtYahooAPI {

	public String getStock(String stockName) throws IOException {
		Stock stock = YahooFinance.get(stockName);
		return String.valueOf(stock.getQuote().getPrice());
	}
	
	public Map<String, Stock> getStock(String[] stockName) throws IOException {;
		return YahooFinance.get(stockName);
	}
	
	public static void main(String[] args) throws IOException {
		
		String[] stocks = {"RELIANCE.NS", "TATAPOWER.NS", "TCS.NS"};
		
		MtYahooAPI api = new MtYahooAPI();
//		System.out.println("name of stock: "+api.getStock("RELIANCE.NS"));
		for (Map.Entry<String, Stock> data : api.getStock(stocks).entrySet()) {
			System.out.println("key: "+data.getKey()+" value: "+data.getValue().getQuote().getPrice());
		}
		
	}
}
