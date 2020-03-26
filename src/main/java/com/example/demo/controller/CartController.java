package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String add(@PathVariable int id, HttpSession session, Model model) {
		
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
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
