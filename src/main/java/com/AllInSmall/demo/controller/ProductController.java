package com.AllInSmall.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.AllInSmall.demo.model.Category;
import com.AllInSmall.demo.model.Product;
import com.AllInSmall.demo.repository.CategoryRepository;
import com.AllInSmall.demo.repository.ProductRepository;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/product")
	public String showForm(Product product, Model model) {
		List<Category> category = categoryRepository.findAll();
		model.addAttribute("allCategory", category);
		return "product";
	}
    
	@PostMapping("/product")
	public String addNewProduct(@RequestParam("productName") String name, @RequestParam("price") float price,
			@RequestParam("categoryId") Integer category_id, @RequestParam("imageData") MultipartFile file) {
		try {
			Product product = new Product();
			product.setName(name);
			product.setPrice(price);
			product.setImageData(file.getBytes());
			product.setImageName(file.getOriginalFilename());
			product.setCreatedBy("Thao");
			Category category = categoryRepository.findById(category_id).orElseThrow(() -> new IllegalArgumentException("Invalid category Id"));
			product.setCategory(category);
			productRepository.save(product);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/";

	}
}
