package com.inditex.test.springboot.app.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.test.springboot.app.TestData;
import com.inditex.test.springboot.app.data.ProductRate;
import com.inditex.test.springboot.app.data.RateSelection;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PriceListControllerRestTemplateTest {
	@Autowired
	private TestRestTemplate client;

	ObjectMapper objectMapper;
	
    @LocalServerPort
	private String puerto;

	@BeforeEach
	public void setup() {
		objectMapper = new ObjectMapper();
	}

	@Test
	@DisplayName("petición a las 10:00 del día 14 del producto 35455   para la brand 1")
	void getMostPriorityPriceBySelection() throws Exception {

		String url = getUri("/test/prices/getMostPriorityPriceBySelection");

		String date = "14, 06, 2020";
		String time = "10:00;00";
		Long productId = 35455L;
		Long brandId = 1L;
		UriComponentsBuilder builder = initComponentBuilder(url, date, time, productId, brandId);

		HttpEntity requestEntity = getHeaders();

		ResponseEntity<ProductRate> response = involeUriExchange(builder, requestEntity);

		ProductRate productRate = response.getBody();
		assertNotNull(productRate);

		assertNotNull(response.getBody());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

		assertTrue(TestData.PRODUCT_RATE_1.equals(productRate));

	}
	@Test
	@DisplayName("petición a las 16:00 del día 14 del producto 35455 para la brand 1")
	void checkProductRatesForDate14at16Oclock() {

		
		String url = getUri("/test/prices/getMostPriorityPriceBySelection");

		String date = "14, 06, 2020";
		String time = "16:00;00";
		Long productId = 35455L;
		Long brandId = 1L;
		UriComponentsBuilder builder = initComponentBuilder(url, date, time, productId, brandId);

		HttpEntity requestEntity = getHeaders();

		ResponseEntity<ProductRate> response = involeUriExchange(builder, requestEntity);

		ProductRate productRate = response.getBody();
		assertNotNull(productRate);
		assertNotNull(response.getBody());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

		assertTrue(TestData.PRODUCT_RATE_2.equals(productRate));		
	}	

	private String getUri(String uri) {
		return "http://localhost:" + puerto + uri;
	}

	private ResponseEntity<ProductRate> involeUriExchange(UriComponentsBuilder builder, HttpEntity requestEntity) {
		 ResponseEntity<ProductRate> response = client.exchange(builder.build().encode().toUri(), HttpMethod.GET, requestEntity,
				 ProductRate.class);
		return response;
	}

	private HttpEntity getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/json");
		HttpEntity requestEntity = new HttpEntity<>(headers);
		return requestEntity;
	}

	private UriComponentsBuilder initComponentBuilder(String url, String date, String time, Long productId,
			Long brandId) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("date", date)
				.queryParam("time", time).queryParam("productId", productId).queryParam("brandId", brandId);
		return builder;
	}

}
