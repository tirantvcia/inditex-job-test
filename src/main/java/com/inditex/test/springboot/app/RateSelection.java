package com.inditex.test.springboot.app;

import java.time.LocalDate;
import java.time.LocalTime;

public class RateSelection {
	
	private LocalDate date;
	private LocalTime time;
	private int product;
	private int brand;


	public RateSelection() {
	}


	public RateSelection(LocalDate date, LocalTime time, int product, int brand) {
		this.date = date;
		this.time = time;
		this.product = product; 
		this.brand = brand;
	}

}
