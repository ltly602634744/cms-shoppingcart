package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.AdminRepository;
import com.example.demo.model.UserRepository;
import com.example.demo.model.data.Admin;
import com.example.demo.model.data.User;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AdminRepository adminRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByUsername(username);
		Admin admin = adminRepo.findByUsername(username);
		
		if (user != null) {
			return user;
		}
		
		if (admin != null) {
			return admin;
		}
		
		throw new UsernameNotFoundException("User: " + username + "notfound!");
	}

	
	
	
	
	
	
	
	
	
	
}
