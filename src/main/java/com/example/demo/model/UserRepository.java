package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.data.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
