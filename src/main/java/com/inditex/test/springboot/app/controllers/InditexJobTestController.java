package com.inditex.test.springboot.app.controllers;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.test.springboot.app.dto.response.ProductRateResponse;
import com.inditex.test.springboot.app.services.Facade;

@RestController
@RequestMapping("/test/prices")
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
			@RequestParam("date") @NotNull @Pattern(regexp = "^((0[1-9]|[12]\\d|3[01])-(0[1-9]|1[0-2])-[12]\\d{3})$", message = "Invalid date pattern") String date,
			@RequestParam("time") @NotNull @Pattern(regexp = "^((0[1-9]|1\\d|2[1-3]):([0-5]\\d))$", message = "Invalid time pattern") String time, 
			@RequestParam("productId") @NotNull Long productId,
			@RequestParam("brandId") @NotNull Long brandId) {

		ProductRateResponse mostPriorityPriceBySelection = facade.findMostPriorityPriceBySelection(date, time, productId,
				brandId);

		return ResponseEntity.ok(mostPriorityPriceBySelection);

	}

}
