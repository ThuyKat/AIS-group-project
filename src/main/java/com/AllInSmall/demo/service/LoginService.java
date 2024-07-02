package com.AllInSmall.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.*;

import org.springframework.stereotype.Service;

import com.AllInSmall.demo.model.User;
import com.AllInSmall.demo.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;
	
	public User authenticate(String email, String password) {
		User user = userRepository.getUserByEmailAndPassword(email, password);
		
// use the Pattern class and define validateEmail method.

// return if the user is found or not
		return user;
	}
}
