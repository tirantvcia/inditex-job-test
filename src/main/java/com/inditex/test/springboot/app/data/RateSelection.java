package com.inditex.test.springboot.app.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class RateSelection {
	
	private Date date;
	private Long product;
	private Long brand;


	public RateSelection() {
	}


	public RateSelection(LocalDate date, LocalTime time, Long product, Long brand) {
		this.date = convertToDate(LocalDateTime.of(date, time));
		this.product = product; 
		this.brand = brand;
	}


	public Date getDate() {
		return date;
	}



	public Long getProduct() {
		return product;
	}


	public Long getBrand() {
		return brand;
	}
	
	private Date convertToDate(LocalDateTime dateToConvert) {
	    return java.util.Date
	      .from(dateToConvert.atZone(ZoneId.systemDefault())
	      .toInstant());
	}

}
