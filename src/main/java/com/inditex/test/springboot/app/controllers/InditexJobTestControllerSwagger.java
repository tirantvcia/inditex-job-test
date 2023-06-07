package com.inditex.test.springboot.app.controllers;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.inditex.test.springboot.app.dto.response.ProductRateResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Inditex Proposal test", description = "Test for a Inditex job proposal")
public interface InditexJobTestControllerSwagger {

	public static final String VALID_TIME_PATERN = "^((0[1-9]|1\\d|2[1-3]):([0-5]\\d))$";
	public static final String VALID_DATE_PATTERN = "^((0[1-9]|[12]\\d|3[01])-(0[1-9]|1[0-2])-[12]\\d{3})$";

	@Operation(summary = "find most priority price rate to apply according date, product and brands ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", 
			    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			      schema = @Schema(implementation = ProductRateResponse.class))})
	})
	ResponseEntity<ProductRateResponse> findMostPriorityPriceBySelection(
			 @NotNull @Pattern(regexp = VALID_DATE_PATTERN, message = "Invalid date pattern") 
			 @Parameter(name = "date", description = "date of petition in format dd-MM-yyyy" , example = "12-12-2021") String date, 
			 @NotNull @Pattern(regexp = VALID_TIME_PATERN, message = "Invalid time pattern") 
			 @Parameter(name = "time", description = "time of petition in format HH:mm", example = "12:12") String time, 
			 @NotNull @Parameter(name = "productId", description = "product id to check the price" , example = "35455") Long productId, 
			 @NotNull @Parameter(name = "brandId", description = "brand id where to apply" , example = "1") Long brandId);
	
	


}