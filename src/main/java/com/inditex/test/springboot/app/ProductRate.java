package com.inditex.test.springboot.app;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ProductRate {

	private Integer productId;
	private Integer priceList;
	private Brand brand;
	private Date startDate;
	private Date endDate;
	private Double price;
	
	

	public ProductRate() {
	}
	
	

	public ProductRate(Integer productId, Integer priceList, Brand brand, LocalDateTime startDateTime, LocalDateTime endDateTime,
			Double price) {
		this.productId = productId;
		this.priceList = priceList;
		this.brand = brand;
		this.startDate = convertToDate(startDateTime);
		this.endDate = convertToDate(endDateTime);
		this.price = price;
	}
	
	
	private Date convertToDate(LocalDateTime dateToConvert) {
	    return java.util.Date
	      .from(dateToConvert.atZone(ZoneId.systemDefault())
	      .toInstant());
	}



	public Integer getProductId() {
		return productId;
	}

	public Integer getPriceList() {
		return priceList;
	}

	public Brand getBrand() {
		return brand;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Double getPrice() {
		return price;
	}

}
