package com.market.api.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.utils.CompanySymbol;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class MtYahooAPI {
	
	@Autowired
	private CompanySymbol companySymbol;

	public String getStock(String stockName) throws IOException {
		Stock stock = YahooFinance.get(stockName);
		return String.valueOf(stock.getQuote().getPrice());
	}
	
	public Map<String, Stock> getStocks(String[] stockName) throws IOException {
		String[] name = new String[stockName.length];
		if(stockName != null && stockName.length > 0) {
			for(int i=0; i<stockName.length; i++) {
				name[i] = stockName[i]+".NS";
			}
		}
		return YahooFinance.get(name);
	}
	
	public Map<String, BigDecimal> getStocksPrice(String[] stockName) throws IOException {
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		getStocks(stockName).entrySet().stream().forEach(p -> map.put(p.getKey(), p.getValue().getQuote().getPrice()));
		return map;
	}
	
	public Map<String, BigDecimal> getUpdatedStocks() {
		try {
			StringBuffer buffer = new StringBuffer();
			for(Map.Entry<Integer, Map<String, Boolean>> datas : companySymbol.map.entrySet()) {
				for(Map.Entry<String, Boolean> data : datas.getValue().entrySet()) {
					if(data.getValue()==true)
						buffer.append(data.getKey()+",");	
				}	
			}
			buffer.deleteCharAt(buffer.length()-1);
			return getStocksPrice(buffer.toString().split(","));
		} catch (Exception e) {e.getMessage();}
		return new HashMap<String, BigDecimal>();
	}
	
	public static void main(String[] args) throws IOException {
		
		String[] stocks = {"RELIANCE.NS", "TATAPOWER.NS", "TCS.NS"};
		
		MtYahooAPI api = new MtYahooAPI();
//		System.out.println("name of stock: "+api.getStock("RELIANCE.NS"));
		for (Map.Entry<String, Stock> data : api.getStocks(stocks).entrySet()) {
			System.out.println("key: "+data.getKey()+" value: "+data.getValue().getQuote().getPrice());
		}
		
	}
}
