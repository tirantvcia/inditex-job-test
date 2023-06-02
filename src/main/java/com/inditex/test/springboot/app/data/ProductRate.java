package com.inditex.test.springboot.app.data;

import java.util.Date;

import com.inditex.test.springboot.app.models.Brand;
import com.inditex.test.springboot.app.models.Price;



public class ProductRate {

	private Long productId;
	private Long priceList;
	private Brand brand;
	private Date startDate;
	private Date endDate;
	private Double price;
	
	

	public ProductRate() {
	}
	
	

	private ProductRate(Long productId, Long priceList, Brand brand, Date startDateTime, Date endDateTime,
			Double price) {
		this.productId = productId;
		this.priceList = priceList;
		this.brand = brand;
		this.startDate = startDateTime;
		this.endDate = endDateTime;
		this.price = price;
	}
	
	
	public static ProductRate create(Price p) {
		if(p != null) {
			return new ProductRate(p.getProductId(), p.getId()
					, p.getBrand(), p.getStartDate(), p.getEndDate(), p.getPrice());
					
		}
		return new ProductRate();
	}



	public Long getProductId() {
		return productId;
	}

	public Long getPriceList() {
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
