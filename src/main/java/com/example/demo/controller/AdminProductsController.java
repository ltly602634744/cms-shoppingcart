package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.CategoryRepository;
import com.example.demo.model.ProductRepository;
import com.example.demo.model.data.Category;
import com.example.demo.model.data.Page;
import com.example.demo.model.data.Product;

@Controller
@RequestMapping("/admin/products")
public class AdminProductsController {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private CategoryRepository categoryRepo;
	
	@GetMapping
	public String index(Model model) {
		
		List<Product> products = this.productRepo.findAll();
		
		model.addAttribute("products", products);
		
		return "/admin/products/index";
	}
	
	@GetMapping("/add")
	public String add(Product product, Model model) {
		
		List<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);
		
		return "admin/products/add";
	}










}
