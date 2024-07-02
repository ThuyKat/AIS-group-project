package com.AllInSmall.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.AllInSmall.demo.model.User;
import com.AllInSmall.demo.service.*;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}

	@PostMapping("/login")
	public String doLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
		// Authenticate via LoginService
		User user = loginService.authenticate(email, password);
		if (user != null) {
			return "redirect:/index";
		} else {
			model.addAttribute("error", "Invalid email or password");
			return "login";
		}
	}

}
