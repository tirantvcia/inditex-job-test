package com.inditex.test.springboot.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inditex.test.springboot.app.models.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long>{
}
