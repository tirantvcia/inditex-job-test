package com.inditex.test.springboot.app.models;

import java.util.Currency;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PRICES")
public class Price {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@Column(name = "START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Column(name = "END_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	@Column(name = "PRODUCT_ID")
	private Long productId;
	@Column(name = "PRIORITY")
	private int priority;
	@Column(name = "PRICE")
	private Double price;
	@Column(name = "CURR")
	private Currency currency;
	
	
	public Price() {
	}

	
	
	public Price(Long id, Brand brand, Date startDate, Date endDate, Long productId, int priority,
			Double price, Currency currency) {
		this.id = id;
		this.brand = brand;
		this.startDate = startDate;
		this.endDate = endDate;
		this.productId = productId;
		this.priority = priority;
		this.price = price;
		this.currency = currency;
	}



	public Long getId() {
		return id;
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


	public Long getProductId() {
		return productId;
	}

	public int getPriority() {
		return priority;
	}

	public Double getPrice() {
		return price;
	}

	public Currency getCurrency() {
		return currency;
	}
	
	

	public void setId(Long id) {
		this.id = id;
	}



	public void setBrand(Brand brand) {
		this.brand = brand;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public void setProductId(Long productId) {
		this.productId = productId;
	}



	public void setPriority(int priority) {
		this.priority = priority;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public void setCurrency(Currency currency) {
		this.currency = currency;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + priority;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		Price other = (Price) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (priority != other.priority)
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
	
	
}
