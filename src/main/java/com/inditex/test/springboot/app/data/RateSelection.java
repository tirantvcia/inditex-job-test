package com.inditex.test.springboot.app.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class RateSelection implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 546828601249254905L;
	private Date date;
	private Long product;
	private Long brand;

	private RateSelection() {
	}
	
	private RateSelection(Date date, Long product, Long brand) {
		this.date = date;
		this.product = product;
		this.brand = brand;
	}

	public static RateSelection create(String date, String time, Long productId, Long brandId) {
		try {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
			LocalDate petitionDate = LocalDate.parse(date, dateFormatter);
			LocalTime petitionTime = LocalTime.parse(time, timeFormatter);
			return new RateSelection(convertToDate(LocalDateTime.of(petitionDate, petitionTime)), productId, brandId);
		} catch (Exception ex) {
			return new RateSelection();
		}
	}
	
	private static Date convertToDate(LocalDateTime dateToConvert) {
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
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





}
