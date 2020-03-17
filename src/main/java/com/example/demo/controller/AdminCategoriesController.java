package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.CategoryRepository;
import com.example.demo.model.data.Category;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoriesController {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@GetMapping()
	public String index(Model model) {
		
		List<Category> categories = this.categoryRepo.findAll();
		
		model.addAttribute("categories", categories);
		
		return "admin/categories/index";
		
	}
}

















