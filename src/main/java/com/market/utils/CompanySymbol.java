package com.market.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CompanySymbol {
	
	public static Map<Integer, Map<String, Boolean>> map = new HashMap<Integer, Map<String,Boolean>>();
	Map<String, Boolean> child = null;
	
	@PostConstruct
	public void initCompanySymbol() {
		map.put(1, add("ADANIENT",false));
		map.put(2, add("ADANIPOWER",false));
		map.put(3, add("ADANIGAS",false));
		map.put(4, add("ADANIPORTS",false));
		map.put(5, add("ADANIGREEN",false));
		
		map.put(6, add("RIIL",false));
		map.put(7, add("RELINFRA",false));
		map.put(8, add("RELIANCE",false));
		map.put(9, add("RELCAPITAL",false));
		map.put(10, add("RCOM",false));
		
		map.put(11, add("TATACHEM",false));
		map.put(12, add("TATACOFFEE",false));
		map.put(13, add("TATACOMM",false));
		map.put(14, add("TATAELXSI",false));
		map.put(15, add("TATAGLOBAL",false));
		map.put(16, add("TATAINVEST",false));
		map.put(17, add("TATAMETALI",false));
		map.put(18, add("TATAMOTORS",false));
	}

	public Map<String, Boolean> add(String name, Boolean value){
		child = new HashMap<String, Boolean>();
		child.put(name, value);
		return child;
	}
	
	
	public Map<Integer, Map<String, Boolean>> updateSymbol(Integer index, Boolean status) {
		for(Map.Entry<String, Boolean> data : map.get(index).entrySet())
			map.put(index, add(data.getKey(), status));
		return map;
	}
}
