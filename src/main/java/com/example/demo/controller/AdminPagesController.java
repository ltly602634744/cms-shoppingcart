package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.PageRepository;
import com.example.demo.model.data.Page;

@Controller
@RequestMapping("/admin/pages")
public class AdminPagesController {

	private PageRepository pageRepo;
	
	
	public AdminPagesController(PageRepository pageRepo) {
		this.pageRepo = pageRepo;
	}


	@GetMapping
	public String index(Model model) {
		
		List<Page> pages = pageRepo.findAll();
		
		model.addAttribute("pages", pages);
		
		return"/admin/pages/index";
	}
}















