package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.data.Page;

public interface PageRepository extends JpaRepository<Page, Integer> {
	
	Page findBySlug(String slug);
	
	/*
	 * Two methods to implement findBySlug
	 * This is a method to use when we want to edit
	 * it can avoid we change the slug to an exist slug
	 * However, when we do not change the slug, it should pass,
	 * so we need to check whether the duplicate slug is current id
	 */
//	@Query("SELECT p FROM Page p WHERE p.id != :id and p.slug = :slug")
//	Page findBySlug(int id, String slug);
	Page findBySlugAndIdNot(String slug, int id);
	
	List<Page> findAllByOrderBySortingAsc();
}
