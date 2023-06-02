package com.inditex.test.springboot.app.repositories;

import java.util.List;

import com.inditex.test.springboot.app.models.Brand;

public interface BrandRepository {
	List<Brand> findAll();
	Brand findById(Long id);
}
