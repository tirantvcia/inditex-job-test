package com.inditex.test.springboot.app.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inditex.test.springboot.app.dto.response.ProductRateResponse;

@Component
public class FacadeImpl implements Facade {

	private ZaraProductRateService service;

	public FacadeImpl() {
	}

	@Autowired
	public FacadeImpl(ZaraProductRateService service) {
		this.service = service;
	}

	@Override
	public ProductRateResponse findMostPriorityPriceBySelection(String date, String time, Long productId, Long brandId) {
		Date localDateTimeCriteria = mapDateTimeCriteriaToLocalDateTime(date, time);
		return service.findMostPriorityPriceBySelection(localDateTimeCriteria, productId, brandId);
	}

	private Date mapDateTimeCriteriaToLocalDateTime(String date, String time) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalDate petitionDate = LocalDate.parse(date, dateFormatter);
		LocalTime petitionTime = LocalTime.parse(time, timeFormatter);
		return convertToDate(LocalDateTime.of(petitionDate, petitionTime));
	}

	private Date convertToDate(LocalDateTime dateToConvert) {
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}
	
}
