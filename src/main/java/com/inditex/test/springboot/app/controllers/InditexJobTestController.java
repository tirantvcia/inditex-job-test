package com.inditex.test.springboot.app.controllers;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.test.springboot.app.dto.response.ProductRateResponse;
import com.inditex.test.springboot.app.services.Facade;

@RestController
@RequestMapping("/test/prices")
@Validated
public class InditexJobTestController implements InditexJobTestControllerSwagger{

	private Facade facade;

	public InditexJobTestController() {
	}

	@Autowired
	public InditexJobTestController(Facade facade) {
		this.facade = facade;
	}

	@Override
	@GetMapping("/getMostPriorityPriceBySelection")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<ProductRateResponse> findMostPriorityPriceBySelection(
			@RequestParam("date") String date,
			@RequestParam("time") String time, 
			@RequestParam("productId") Long productId,
			@RequestParam("brandId") Long brandId) {

		ProductRateResponse mostPriorityPriceBySelection = facade.findMostPriorityPriceBySelection(date, time, productId, brandId);
		return ResponseEntity.ok(mostPriorityPriceBySelection);

	}
	  @ExceptionHandler(ConstraintViolationException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
	    return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	  }
}
