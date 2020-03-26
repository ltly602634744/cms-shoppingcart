package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.Cart;
import com.example.demo.model.CategoryRepository;
import com.example.demo.model.PageRepository;
import com.example.demo.model.data.Category;
import com.example.demo.model.data.Page;

@ControllerAdvice
@SuppressWarnings("unchecked")
public class Common {

	@Autowired
	private PageRepository pageRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@ModelAttribute
	public void sharedData(Model model, HttpSession session) {
		
		List<Page> pages = pageRepo.findAllByOrderBySortingAsc();
		
		List<Category> categories = categoryRepo.findAll();
		
		boolean cartActive = false;
		
		if (session.getAttribute("cart") != null) {
			
			Map<Integer, Cart> cart = (HashMap<Integer, Cart>)session.getAttribute("cart");
			
			int size = 0;
			double total = 0;
			
			for (Cart value : cart.values()) {
				size += value.getQuantity();
				total += value.getQuantity() * Double.parseDouble(value.getPrice());
			}
			
			model.addAttribute("csize", size);
			model.addAttribute("ctotal", total);
		
			cartActive = true;
		}
		
		model.addAttribute("cpages", pages);
		model.addAttribute("ccategories", categories);
		model.addAttribute("cartActive", cartActive);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
