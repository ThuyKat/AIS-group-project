package com.AllInSmall.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.AllInSmall.demo.model.User;
import com.AllInSmall.demo.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
private UserRepository userRepository;
	@GetMapping()
	public Iterable<User>getAllUser(){
		Iterable<User>users = userRepository.findAll();
		return users;
	}
}
