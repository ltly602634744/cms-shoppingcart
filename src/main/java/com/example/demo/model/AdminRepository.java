package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.data.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

}
