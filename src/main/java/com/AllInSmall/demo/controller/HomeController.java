package com.AllInSmall.demo.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.AllInSmall.demo.model.Category;
import com.AllInSmall.demo.model.Order;
import com.AllInSmall.demo.model.OrderDetail;
import com.AllInSmall.demo.model.OrderDetailKey;
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
	private final Order sessionOrder;

	@Autowired
	public HomeController(CategoryRepository categoryRepository, ProductRepository productRepository,
			OrderDetailRepository orderDetailRepository, @Qualifier("sessionOrder") Order sessionOrder) {

		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
		this.orderDetailRepository = orderDetailRepository;
		this.sessionOrder = sessionOrder;

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
		model.addAttribute("order", sessionOrder);
		return "viewOrder";

	}

	@GetMapping("/resetOrder")
	public ResponseEntity<String> resetOrder() {
		// clear the session-scoped Order bean
		sessionOrder.reset();
		String message = "Order is reset" + sessionOrder.getOrderDetails().toString();
		return ResponseEntity.ok(message);
	}

	// NEED JAVA SCRIPT TO CAPTURE PRODUCT ID AND QUANTITY INTO MAP<INTEGER,INTEGER>
	@PutMapping("/addToOrder")
	public ResponseEntity<String> addToOrder(@RequestBody Map<Integer, Integer> orderItems) {

		try {
			// new order instance
			for (Map.Entry<Integer, Integer> orderItem : orderItems.entrySet()) {
				System.out.println("for each order item: get product id and quantity");
				// get id and quantity of each product
				int productId = orderItem.getKey();
				int quantity = orderItem.getValue();

				// find the product in DB by productId
				Product product = productRepository.findById(productId);

				Set<OrderDetail> sessionSetOfOrderDetails = sessionOrder.getOrderDetails();
				// product is found, check if sessionOrder has any orderDetails
				if (product != null) {
					if (sessionSetOfOrderDetails == null) {
						sessionOrder.setOrderDetails(new HashSet<>());
						// Create OrderDetailKey
						OrderDetailKey orderDetailKey = new OrderDetailKey(sessionOrder.getId(), productId);

						// new order and orderDetail instance

						OrderDetail orderDetail = new OrderDetail();
						// build orderDetail
						orderDetail = OrderDetail.builder().id(orderDetailKey) // Set the composite key
								.product(product).quantity(quantity).order(sessionOrder).createdBy("thuy")
								.createdDate(LocalDateTime.now()).build();

						// add orderDetail to order

						// Add the new OrderDetail
						sessionOrder.getOrderDetails().add(orderDetail);

					} else {
						// check if the product has already in sessionOrder.getOrderDetails
						boolean productInOrderDetail = false;
						for (OrderDetail orderDetail : sessionSetOfOrderDetails) {
							if (orderDetail.getProduct().getId() == productId) {
								Integer updatedQuantity = orderDetail.getQuantity() + quantity;
								orderDetail.setQuantity(updatedQuantity);
								productInOrderDetail = true;
								break;

							}
						}

						if (productInOrderDetail == false) {
							// if the product is not in any orderDetail of the session
							// initiate a new OrderDetail and add it to the sessionOrder

							// Create OrderDetailKey
							OrderDetailKey orderDetailKey = new OrderDetailKey(sessionOrder.getId(), productId);

							// new order and orderDetail instance

							OrderDetail orderDetail = new OrderDetail();
							// build orderDetail
							orderDetail = OrderDetail.builder().id(orderDetailKey) // Set the composite key
									.product(product).quantity(quantity).order(sessionOrder).createdBy("thuy")
									.createdDate(LocalDateTime.now()).build();

							// add orderDetail to order

							// Add the new OrderDetail
							sessionOrder.getOrderDetails().add(orderDetail);

						}

					}
					// product is not found
				} else {
					return ResponseEntity.ok("Nothing is added, no product is found");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok("Nothing is added");
		}
		String message = "successfully added : " + sessionOrder.getOrderDetails().toString();
		
		return ResponseEntity.ok(message);
	}

}
