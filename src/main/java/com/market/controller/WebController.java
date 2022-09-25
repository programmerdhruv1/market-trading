package com.market.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.market.api.service.MtYahooAPI;
import com.market.utils.CompanySymbol;
import com.market.utils.GenericResponse;

@Controller
public class WebController {
	
	@Autowired
	private CompanySymbol companySymbol;

	@Autowired
	private MtYahooAPI mtYahooAPI;
	
	@RequestMapping({"","/"})
	public String index(HttpServletRequest httpServletRequest, Model model) {
		try {
			model.addAttribute("stocks", mtYahooAPI.getUpdatedStocks());
		} catch (Exception e) {}
		model.addAttribute("symbols", companySymbol.map);
		return "index";
	}
	
	@RequestMapping("/updatStocks")
	public String getUpdatStocks(Model model){
		try {
			model.addAttribute("stocks", mtYahooAPI.getUpdatedStocks());
		} catch (Exception e) {}
		return "stockData";
	}
	
	@RequestMapping("/updatSymbols")
	public String getUpdatSymbols(@RequestParam("id") Integer id, @RequestParam("flag") Boolean flag, Model model){
		try {
			model.addAttribute("symbols", companySymbol.updateSymbol(id, !flag));
			return "symbolData";
		} catch (Exception e) {
			model.addAttribute("symbols", companySymbol.map);
			return "symbolData";
		}
	}
	
}
