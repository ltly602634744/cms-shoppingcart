package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.data.Admin;
import com.example.demo.model.data.User;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	Admin findByUsername(String username);
}
