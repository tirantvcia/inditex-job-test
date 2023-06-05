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

	private RateSelection(LocalDate date, LocalTime time, Long product, Long brand) {
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
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static RateSelection create(String date, String time, Long productId, Long brandId) {
		try {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
			LocalDate petitionDate = LocalDate.parse(date, dateFormatter);
			LocalTime petitionTime = LocalTime.parse(time, timeFormatter);
			return new RateSelection(petitionDate, petitionTime, productId, brandId);
		} catch (Exception ex) {
			return new RateSelection();
		}
	}

}
