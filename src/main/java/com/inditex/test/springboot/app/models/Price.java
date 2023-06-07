package com.inditex.test.springboot.app.models;

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
@Table(name = "PRICES")
public class Price {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
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
	private String currency;

	public Price() {
	}

	public Price(Long id, Brand brand, Date startDate, Date endDate, Long productId, int priority, Double price,
			String currency) {
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

	public String getCurrency() {
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

	public void setCurrency(String currency) {
		this.currency = currency;
	}


}
