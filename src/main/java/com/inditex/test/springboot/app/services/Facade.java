package com.inditex.test.springboot.app.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inditex.test.springboot.app.data.ProductRate;
import com.inditex.test.springboot.app.data.RateSelection;
import com.inditex.test.springboot.app.models.Brand;
import com.inditex.test.springboot.app.models.Price;

@Component
public class Facade {

	private ZaraProductRateService service;

	public Facade() {
	}

	@Autowired
	public Facade(ZaraProductRateService service) {
		this.service = service;
	}

	public ProductRate findMostPriorityPriceBySelection(String date, String time, Long productId, Long brandId) {

		LocalDateTime dateTime = toLocalDateTime(date, time);
		RateSelection entry = new RateSelection(LocalDate.of(2022, 3, 14), LocalTime.of(10, 00), 34455L, 1L);
		Brand Brand_ZARA = new Brand(1L, "ZARA");
		Price PRICE_LIST_1 = new Price(1L, Brand_ZARA, convertToDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0)),
				convertToDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59)), 35455L, 0, 35.5, "EUR");
		ProductRate PRODUCT_RATE_1 = ProductRate.create(PRICE_LIST_1);
		return PRODUCT_RATE_1;

	}

	public LocalDateTime toLocalDateTime(String date, String time) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalDate petitionDate = LocalDate.parse(date, dateFormatter);
		LocalTime petitionTime = LocalTime.parse(time, timeFormatter);
		return LocalDateTime.of(petitionDate, petitionTime);
	}

	private Date convertToDate(LocalDateTime dateToConvert) {
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}
}
