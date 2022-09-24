package com.market;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.market.api.MtYahooAPI;
import com.market.utils.GenericResponse;

@RestController
@RequestMapping("/stock/in/v1")
public class ApiController {

	@Autowired
	private MtYahooAPI mtYahooAPI;

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
			return ResponseEntity.ok(new GenericResponse(true, "success", mtYahooAPI.getStock(symbol.split(",")), 200));
		} catch (IOException e) {
			return ResponseEntity.ok(new GenericResponse(false, "error", null, 500));
		}
	}

}
