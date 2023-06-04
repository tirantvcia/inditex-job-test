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
import com.inditex.test.springboot.app.services.Facade;

@RestController
@RequestMapping("/test/prices")
public class PriceListController {

	private Facade facade;

	public PriceListController() {
	}

	@Autowired
	public PriceListController(Facade facade) {
		this.facade = facade;
	}

	@GetMapping("/getMostPriorityPriceBySelection")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<?> findMostPriorityPriceBySelection(@RequestParam("date") String date,
			@RequestParam("time") String time, @RequestParam("productId") Long productId,
			@RequestParam("brandId") Long brandId) {

		ProductRate mostPriorityPriceBySelection = facade.findMostPriorityPriceBySelection(date, time, productId,
				brandId);

		return ResponseEntity.ok(mostPriorityPriceBySelection);

	}

}
