package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.data.Page;

public interface PageRepository extends JpaRepository<Page, Integer> {
	
}
