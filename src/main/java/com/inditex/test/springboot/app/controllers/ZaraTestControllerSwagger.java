package com.inditex.test.springboot.app.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.inditex.test.springboot.app.dto.response.ProductRateResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Selector Precios", description = "Price rate to apply according date, product and brands")
public interface ZaraTestControllerSwagger {

	@Operation(summary = "find most priority price rate to apply according ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", 
			    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			      schema = @Schema(implementation = ProductRateResponse.class))})
	})
	ResponseEntity<ProductRateResponse> findMostPriorityPriceBySelection(String date, String time, Long productId, Long brandId);

}