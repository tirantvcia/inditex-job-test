package com.inditex.test.springboot.app.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.test.springboot.app.data.ProductRate;
import com.inditex.test.springboot.app.models.Brand;
import com.inditex.test.springboot.app.models.Price;
import com.inditex.test.springboot.app.services.ZaraProductRateService;

@RestController
@RequestMapping("/test/prices")
public class PriceListController {

	
	private	ZaraProductRateService service;

	public PriceListController() {
	}



	@Autowired
	public PriceListController(ZaraProductRateService service) {
		this.service = service;
	}
	
	

	@GetMapping("/getMostPriorityPriceBySelection")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<?> findMostPriorityPriceBySelection(@RequestParam("date") String date,
			@RequestParam("time") String time,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {
		Brand Brand_ZARA = new Brand(1L, "ZARA");
		Price PRICE_LIST_1 = new Price(1L, Brand_ZARA, 
				convertToDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0)), 
				convertToDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
				, 35455L, 0, 35.5, "EUR");
		ProductRate PRODUCT_RATE_1 = ProductRate.create(PRICE_LIST_1);
		
	return ResponseEntity.ok(PRODUCT_RATE_1);
		
		
	}
	private static Date convertToDate(LocalDateTime dateToConvert) {
	    return java.util.Date
	      .from(dateToConvert.atZone(ZoneId.systemDefault())
	      .toInstant());
	}
}
