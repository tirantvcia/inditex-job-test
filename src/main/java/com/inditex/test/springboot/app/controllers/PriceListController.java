package com.inditex.test.springboot.app.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.test.springboot.app.data.ProductRate;
import com.inditex.test.springboot.app.data.RateSelection;
import com.inditex.test.springboot.app.services.ZaraProductRateService;

@RestController
@RequestMapping("/test/prices")
public class PriceListController {

	
	private	ZaraProductRateService service;

	@Autowired
	public PriceListController(ZaraProductRateService service) {
		this.service = service;
	}
	
	
	
	@GetMapping("/getMostPriorityPriceBySelection")
	public ResponseEntity<?> getMostPriorityPriceBySelection(@RequestBody RateSelection rateSelection) {
		ProductRate productRate = service.getProductRate(rateSelection);
		Map<String, Object> response = new HashMap<>();
		response.put("productRate", productRate);
		response.put("status", "OK");
		return ResponseEntity.ok(response);
		
		
	}
}
