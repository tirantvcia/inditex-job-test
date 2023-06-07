package com.inditex.test.springboot.app.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


public class RateSelectionRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 546828601249254905L;
	
	@DateTimeFormat(pattern= "dd-MM-yyyy")
	@NotEmpty
	private String date;
	
	@DateTimeFormat(pattern= "HH:mm")
	@NotEmpty
	private String time;
	
	@NotNull
	private Long product;
	
	@NotNull
	private Long brand;
	
	
	public RateSelectionRequest() {
	}


	public RateSelectionRequest(String date, String time, Long product, Long brand) {
		this.date = date;
		this.time = time;
		this.product = product;
		this.brand = brand;
	}


	public String getDate() {
		return date;
	}


	public String getTime() {
		return time;
	}


	public Long getProduct() {
		return product;
	}


	public Long getBrand() {
		return brand;
	}
	
}
