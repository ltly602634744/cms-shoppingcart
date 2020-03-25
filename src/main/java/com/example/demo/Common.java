package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.PageRepository;
import com.example.demo.model.data.Page;

@ControllerAdvice
public class Common {

	@Autowired
	private PageRepository pageRepo;
	
	@ModelAttribute
	public void sharedData(Model model) {
		
		List<Page> pages = pageRepo.findAllByOrderBySortingAsc();
		
		model.addAttribute("cpages", pages);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
