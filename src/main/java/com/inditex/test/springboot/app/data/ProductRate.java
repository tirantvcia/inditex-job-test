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
		if (p != null) {
			return new ProductRate(p.getProductId(), p.getId(), p.getBrand(), p.getStartDate(), p.getEndDate(),
					p.getPrice());

		}
		return new ProductRate();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((priceList == null) ? 0 : priceList.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductRate other = (ProductRate) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (priceList == null) {
			if (other.priceList != null)
				return false;
		} else if (!priceList.equals(other.priceList))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
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
