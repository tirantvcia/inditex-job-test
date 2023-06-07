package com.inditex.test.springboot.app.dto.response;

import java.io.Serializable;
import java.util.Date;

import com.inditex.test.springboot.app.models.Price;

public class ProductRateResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9058136822528153180L;
	private Long productId;
	private Long priceList;
	private Long brandId;
	private Date startDate;
	private Date endDate;
	private Double price;

	public ProductRateResponse() {
	}

	private ProductRateResponse(Long productId, Long priceList, Long brandId, Date startDateTime, Date endDateTime,
			Double price) {
		this.productId = productId;
		this.priceList = priceList;
		this.brandId = brandId;
		this.startDate = startDateTime;
		this.endDate = endDateTime;
		this.price = price;
	}

	public static ProductRateResponse create(Price p) {
			return new ProductRateResponse(p.getProductId(), p.getId(), p.getBrand().getId(), p.getStartDate(), p.getEndDate(),
					p.getPrice());

	}



	public Long getProductId() {
		return productId;
	}

	public Long getPriceList() {
		return priceList;
	}

	public Long getBrandId() {
		return brandId;
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
