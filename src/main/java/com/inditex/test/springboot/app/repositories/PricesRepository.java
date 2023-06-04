package com.inditex.test.springboot.app.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inditex.test.springboot.app.models.Price;

public interface PricesRepository extends JpaRepository<Price, Long>{
	

	@Query("select p from Price p where p.startDate <= :petitionDate and p.endDate >= :petitionDate and p.productId = :productId and p.brand.id = :brandId order by p.priority desc")
	public List<Price> findPricesBySelectionOrderedByPrioriry(@Param("petitionDate") Date petitionDate, @Param("productId") Long productId, @Param("brandId") Long brandId);

}
