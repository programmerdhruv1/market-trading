package com.market.api.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.market.api.service.MtYahooAPI;
import com.market.utils.CompanySymbol;
import com.market.utils.GenericResponse;

import yahoofinance.Stock;

@RestController
@RequestMapping("/stock/in/v1")
public class ApiController {

	@Autowired
	private MtYahooAPI mtYahooAPI; 
	
	@Autowired
	private CompanySymbol companySymbol;
	
	@RequestMapping("/price")
	public ResponseEntity<GenericResponse> getSingleStock(@RequestParam("symbol") String symbol) {
		try {
			return ResponseEntity.ok(new GenericResponse(true, "success", mtYahooAPI.getStock(symbol), 200));
		} catch (IOException e) {
			return ResponseEntity.ok(new GenericResponse(false, "error", null, 500));
		}
	}
	
	@RequestMapping("/prices")
	public ResponseEntity<GenericResponse> getMultipleStock(@RequestParam("symbol") String symbol) {
		try {
			return ResponseEntity.ok(new GenericResponse(true, "success", mtYahooAPI.getUpdatedStocks(), 200));
		} catch (Exception e) {
			return ResponseEntity.ok(new GenericResponse(false, "error", null, 500));
		}
	}
	
	@RequestMapping("/symbols")
	public ResponseEntity<GenericResponse> getUpdatSymbols(){
		try {
			return ResponseEntity.ok(new GenericResponse(true, "success", companySymbol.map, 200));
		} catch (Exception e) {
			return ResponseEntity.ok(new GenericResponse(false, "error", null, 500));
		}
	}
	
	@RequestMapping("/updatSymbols")
	public ResponseEntity<GenericResponse> getUpdatSymbols(@RequestParam("id") Integer id, @RequestParam("flag") Boolean flag){
		try {
			return ResponseEntity.ok(new GenericResponse(true, "success", companySymbol.updateSymbol(id, !flag), 200));
		} catch (Exception e) {
			return ResponseEntity.ok(new GenericResponse(false, "error", null, 500));
		}
	}

}
