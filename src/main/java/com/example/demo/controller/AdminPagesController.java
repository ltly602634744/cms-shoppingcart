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

import com.example.demo.model.PageRepository;
import com.example.demo.model.data.Page;

@Controller
@RequestMapping("/admin/pages")
public class AdminPagesController {

	@Autowired
	private PageRepository pageRepo;
	
//	public AdminPagesController(PageRepository pageRepo) {
//		this.pageRepo = pageRepo;
//	}

	@GetMapping
	public String index(Model model) {
		
		List<Page> pages = pageRepo.findAll();
		
		model.addAttribute("pages", pages);
		
		return"/admin/pages/index";
	}
	
	@GetMapping("/add")
	public String add(@ModelAttribute Page page) {
//		public String add(Model model) {
		
		//model.addAttribute("page", new Page());
		
		return "admin/pages/add";
	}
	
	@PostMapping("/add")
	public String add(@Valid Page page, BindingResult bindingResult,
						RedirectAttributes redirectAttributes, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "admin/pages/add";
		}
		
		redirectAttributes.addFlashAttribute("message", "Page added");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		
		String slug = page.getSlug() == "" ? page.getTitle().toLowerCase().replace(" ", "-") : page.getSlug().toLowerCase().replace(" ", "-");
		
		Page slugExists = pageRepo.findBySlug(slug);
		
		if( slugExists != null ) {
			redirectAttributes.addFlashAttribute("message", "Slug exists, choose another");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			redirectAttributes.addFlashAttribute("page", page);
		}else {
			page.setSlug(slug);
			page.setSorting(100);
			
			pageRepo.save(page);
		}
		
		return "redirect:/admin/pages/add";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		
		Page page = pageRepo.getOne(id);
		
		model.addAttribute("page", page);
		
		return "admin/pages/edit";
	}
	
	/**
	 * 
	 * @param page  					the current page object
	 * @param bindingResult  			to check whether the page object is valid
	 * @param redirectAttributes 		show the information view
	 * @param model						the model of the current page object
	 * @return
	 * 
	 * Steps:
	 * 1. check whether the input information is valid, if not, show the warning
	 * 2. if not, show the successful view
	 * 3. get the slug
	 * 
	 * check whether the slug is exist and the exist page is not the current page
	 * 4. using the method in Spring JPA to get the exist slug
	 * 		(using sql language or specific method declare in the PageRepository interface)
	 * 5. check whether the slug is exist, 
	 * if it is, show the warning view and let the user to choose another one
	 * 6. if not, set the new slug and save the page
	 * 7. return the new url
	 */
	@PostMapping("/edit")
	public String edit(@Valid Page page, BindingResult bindingResult,
						RedirectAttributes redirectAttributes, Model model) {
		
		Page currentPage = pageRepo.getOne(page.getId());
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", currentPage.getTitle());
			return "admin/pages/edit";
		}
		
		redirectAttributes.addFlashAttribute("message", "Page edited");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		
		String slug = page.getSlug() == "" ? page.getTitle().toLowerCase().replace(" ", "-") : page.getSlug().toLowerCase().replace(" ", "-");
		
		/*
		 *You can uncommon the method in the PageRepository class, 
		 *use this code to ge the slugExist object
		Page slugExists = pageRepo.findBySlug(page.getId(), slug);
		 */
		Page slugExists = pageRepo.findBySlugAndIdNot(slug, page.getId());
		
		if( slugExists != null ) {
			redirectAttributes.addFlashAttribute("message", "Slug exists, choose another");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			redirectAttributes.addFlashAttribute("page", page);
		}else {
			page.setSlug(slug);
			
			pageRepo.save(page);
		}
		
		return "redirect:/admin/pages/edit/" + page.getId();
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
		
		pageRepo.deleteById(id);
		
		redirectAttributes.addFlashAttribute("message", "Page deleted");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		
		return "redirect:/admin/pages";
	}
}















