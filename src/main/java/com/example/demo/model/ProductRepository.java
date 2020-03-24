package com.example.demo.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.data.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	Product findBySlug(String slug);

	Product findBySlugAndIdNot(String slug, int id);
	
	Page<Product> findAll(Pageable pageable);

}
