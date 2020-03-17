package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.data.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Category findByName(String name);
	
	List<Category> findAllByOrderBySortingAsc();

}
