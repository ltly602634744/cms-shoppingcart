package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.ProductRepository;
import com.example.demo.model.data.Product;

@Controller
@RequestMapping("/admin/products")
public class AdminProductsController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	public String index(Model model) {
		
		List<Product> products = this.productRepository.findAll();
		
		model.addAttribute("products", products);
		
		return "/admin/products/index";
	}










}
