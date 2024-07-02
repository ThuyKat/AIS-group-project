package com.AllInSmall.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.AllInSmall.demo.model.Category;

import com.AllInSmall.demo.repository.CategoryRepository;


@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@GetMapping()
	public Iterable<Category>getAllCategories(){
		Iterable<Category>categories = categoryRepository.findAll();
		return categories;
	}
}
