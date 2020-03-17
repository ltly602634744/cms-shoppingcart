package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.CategoryRepository;
import com.example.demo.model.data.Category;
import com.example.demo.model.data.Page;

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
	
	/**
	 * You can define this method to define the ModelAttribute for the 
	 * whole file, if you define it, this java file can use this ModelAttribute
	 * and you do not need to transfer the parameter into the methods
	 * @return
	 */
//	@ModelAttribute("category")
//	public Category getCategory() {
//		return new Category();
//	}
	
	/*
	 * You can just delete the annotation @ModelAttribute, 
	 * then the name of the variable must match the name 
	 * in the add.html file, if you use @ModelAttribute annotation, 
	 * you can change the name by yourself. 
	 */
	@GetMapping("/add")
//	public String sdd(@ModelAttribute Category category) {
//	public String sdd() {
	public String sdd(Category category) {
		return "admin/categories/add";
	}
	
	@PostMapping("/add")
	public String add(@Valid Category category, BindingResult bindingResult,
						RedirectAttributes redirectAttributes, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "admin/categories/add";
		}
		
		redirectAttributes.addFlashAttribute("message", "Category added");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		
		String slug = category.getName().toLowerCase().replace(" ", "-");
		
		Category categoryExists = categoryRepo.findByName(category.getName());
		
		if( categoryExists != null ) {
			redirectAttributes.addFlashAttribute("message", "Category exists, choose another");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			redirectAttributes.addFlashAttribute("categoryInfo", category);
		}else {
			category.setSlug(slug);
			category.setSorting(100);
			
			categoryRepo.save(category);
		}
		
		return "redirect:/admin/categories/add";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		
		Category category = categoryRepo.getOne(id);
		
		model.addAttribute("category", category);
		
		return "admin/categories/edit";
	}
	
	@PostMapping("/edit")
	public String edit(@Valid Category category, BindingResult bindingResult,
						RedirectAttributes redirectAttributes, Model model) {
		
		Category categoryCurrent = categoryRepo.getOne(category.getId());
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", categoryCurrent.getName());
			return "admin/categories/edit";
		}
		
		redirectAttributes.addFlashAttribute("message", "Category edited");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		
		String slug = category.getName().toLowerCase().replace(" ", "-");
		
		Category categoryExists = categoryRepo.findByName(category.getName());
		
		if( categoryExists != null ) {
			redirectAttributes.addFlashAttribute("message", "Category exists, choose another");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		}else {
			category.setSlug(slug);
			
			categoryRepo.save(category);
		}
		
		return "redirect:/admin/categories/edit/" + category.getId();
	}
	
	
}

















