package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Cart;
import com.example.demo.model.ProductRepository;
import com.example.demo.model.data.Product;

@Controller
@RequestMapping("/cart")
@SuppressWarnings("unchecked")
public class CartController {

	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("/add/{id}")
	public String add(@PathVariable int id, 
						HttpSession session, 
						Model model, 
						@RequestParam(value = "cartPage", required = false) String cartPage) {
		
		Product product = productRepo.getOne(id);
		
		if (session.getAttribute("cart") == null) {
			
			Map<Integer, Cart> cart = new HashMap<>();
			cart.put(id, new Cart(id, product.getName(), product.getPrice(), 1, product.getImage()));
			session.setAttribute("cart", cart);
		}else {
			Map<Integer, Cart> cart = (HashMap<Integer, Cart>)session.getAttribute("cart");
			if (cart.containsKey(id)) {
				int quantity = cart.get(id).getQuantity();
				cart.put(id, new Cart(id, product.getName(), product.getPrice(), quantity+1, product.getImage()));
			}else {
				cart.put(id, new Cart(id, product.getName(), product.getPrice(), 1, product.getImage()));
				session.setAttribute("cart", cart);
			}
		}
		
		Map<Integer, Cart> cart = (HashMap<Integer, Cart>)session.getAttribute("cart");
		int size = 0;
		double total = 0;
		
		for (Cart value : cart.values()) {
			size += value.getQuantity();
			total += value.getQuantity() * Double.parseDouble(value.getPrice());
		}
		
		model.addAttribute("size", size);
		model.addAttribute("total", total);
		
		if (cartPage != null) {
			return "redirect:/cart/view";
		}
		
		
		return "cart_view";
	}
	
	@GetMapping("/subtract/{id}")
	public String add(@PathVariable int id, HttpSession session, Model model,
			HttpServletRequest httpServletRequest) {
		
		Product product = productRepo.getOne(id);
		
		Map<Integer, Cart> cart = (HashMap<Integer, Cart>)session.getAttribute("cart");
		
		int quantity = cart.get(id).getQuantity();
		if (quantity == 1) {
			cart.remove(id);
			if(cart.size() == 0) {
				session.removeAttribute("cart");
			}
		}else {
			cart.put(id, new Cart(id, product.getName(), product.getPrice(), quantity-1, product.getImage()));
		}
		
		String refererLink = httpServletRequest.getHeader("referer");
		
		return "redirect:" + refererLink;
	}
	
	
	@RequestMapping("/view")
	public String view(HttpSession session, Model model) {
		if(session.getAttribute("cart") == null) {
			return "redirect:/";
		}
		
		Map<Integer, Cart> cart = (HashMap<Integer, Cart>)session.getAttribute("cart");
		model.addAttribute("cart", cart);
		model.addAttribute("notCartViewPage", true);
		
		return "cart";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
