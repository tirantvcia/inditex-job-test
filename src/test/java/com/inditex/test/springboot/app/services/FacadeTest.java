package com.inditex.test.springboot.app.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class FacadeTest {

	@MockBean
	ZaraProductRateService service;

	@Autowired
	Facade facade;

	@Test
	void toLocalDateTime() {
		String date = "14-03-2022";
		String time = "10:00";
		LocalDateTime localDateTime = facade.toLocalDateTime(date, time);

		LocalDateTime ldt = LocalDateTime.of(LocalDate.of(2022, 3, 14), LocalTime.of(10, 00));
		assertTrue(ldt.equals(localDateTime));
	}

}
