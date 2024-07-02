package com.AllInSmall.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.AllInSmall.demo.model.Category;
import com.AllInSmall.demo.model.Order;
import com.AllInSmall.demo.model.OrderDetail;
import com.AllInSmall.demo.model.Product;
import com.AllInSmall.demo.repository.CategoryRepository;
import com.AllInSmall.demo.repository.OrderDetailRepository;
import com.AllInSmall.demo.repository.ProductRepository;

import org.springframework.ui.Model;

@Controller
public class HomeController {

	private CategoryRepository categoryRepository;
	private ProductRepository productRepository;
	private OrderDetailRepository orderDetailRepository;
	private final Order order;
	

	@Autowired
	public HomeController(CategoryRepository categoryRepository, ProductRepository productRepository,
			OrderDetailRepository orderDetailRepository,Order order) {
		
		super();
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
		this.orderDetailRepository = orderDetailRepository;
		
		this.order = order;
	}

	@GetMapping("/index")
	public String getAllCategories(@RequestParam(required = false) Integer categoryId,
			@RequestParam(required = false) String action, Model model) {
		List<Category> categories = categoryRepository.findAll();
		List<Product> products;

		if ("SHOW_PRODUCT_BY_CATEGORY".equals(action) && categoryId != 0) {
			products = productRepository.findProductByCategoryId(categoryId);

		} else if (("SHOW_ALL").equals(action)) {
			products = productRepository.findAll();

		} else {
			products = productRepository.findAll();

		}
		model.addAttribute("allCategory", categories);
		model.addAttribute("products", products);
//		System.out.println(categories);
		return "index";
	}
	
	@GetMapping("/viewOrder")
	public String viewOrder(Model model) {
		model.addAttribute("order",order);
		return "viewOrder";

	}
	
	@GetMapping("/resetOrder")
	public ResponseEntity<String> resetOrder(){
		// clear the session-scoped Order bean
		order.reset();
		return ResponseEntity.ok("Order is reset");
	}

	// NEED JAVA SCRIPT TO CAPTURE PRODUCT ID AND QUANTITY INTO MAP<INTEGER,INTEGER>
	@PutMapping("/addToOrder")
	public ResponseEntity<String> addToOrder(@RequestBody Map<Integer, Integer> orderItems) {

		try {
			// new order instance
			for (Map.Entry<Integer, Integer> orderItem : orderItems.entrySet()) {
				// get id and quantity of each product
				int productId = orderItem.getKey();
				int quantity = orderItem.getValue();

				// find the product in DB by productId
				Product product = productRepository.findById(productId);
				// check if value > 0, create a new OrderDetail instance and add product +
				// quantity to it
				if (product != null && quantity > 0) {
					// new order and orderDetail instance

					OrderDetail orderDetail = new OrderDetail();
					// build orderDetail
					orderDetail.setProduct(product);
					orderDetail.setQuantity(quantity);
					orderDetail.setOrder(order);
					// save orderDetail to DB
					orderDetailRepository.save(orderDetail);
					// add orderDetail to order 
					order.getOrderDetails().add(orderDetail);
	
				}
			}
		} catch (Exception e) {
			return ResponseEntity.ok("Nothing is added");
		}
		return ResponseEntity.ok("successfully added"); //NEED JAVA SCRIPT FOR THE POP-UP MESSAGE
	}

	
}
