package com.inditex.test.springboot.app.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.test.springboot.app.TestData;
import com.inditex.test.springboot.app.data.RateSelection;
import com.inditex.test.springboot.app.services.ZaraProductRateService;

@WebMvcTest(PriceListController.class)
class PriceListControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ZaraProductRateService service;

	ObjectMapper objectMapper;

	@BeforeEach
	public void setup() {
		objectMapper = new ObjectMapper();
	}

	@Test
	@DisplayName("petición a las 10:00 del día 14 del producto 35455   para la brand 1")
	void getMostPriorityPriceBySelection() throws Exception {
		RateSelection entry = new RateSelection(LocalDate.of(2020, 6, 14), LocalTime.of(10, 00), 35455L, 1L);

		when(service.findMostPriorityPriceBySelection(entry)).thenReturn(TestData.PRODUCT_RATE_1);

		mvc.perform(get("/test/prices/getMostPriorityPriceBySelection").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(entry))).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.priceList").value(1)).andExpect(jsonPath("$.price").value(35.50));
		verify(service).findMostPriorityPriceBySelection(entry);

	}

}
