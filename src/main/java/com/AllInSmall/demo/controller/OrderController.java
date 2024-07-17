package com.AllInSmall.demo.controller;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.AllInSmall.demo.model.Order;
import com.AllInSmall.demo.model.OrderDetail;
import com.AllInSmall.demo.enums.OrderStatus;
import com.AllInSmall.demo.repository.OrderDetailRepository;
import com.AllInSmall.demo.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Controller
public class OrderController {
	
	private final Order sessionOrder;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    
    @Autowired
    public OrderController(@Qualifier("sessionOrder") Order sessionOrder,OrderRepository orderRepository,OrderDetailRepository orderDetailRepository) {
    	this.sessionOrder = sessionOrder;
    	this.orderRepository = orderRepository;
    }
    
    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(Model model) {
    	try {
            if (sessionOrder == null || sessionOrder.getOrderDetails() == null || sessionOrder.getOrderDetails().isEmpty()) {
                return ResponseEntity.badRequest().body("No items in the order");
            }

            // Use @Transactional to ensure atomicity
            return placeOrderTransaction();
        } catch (Exception e) {
//            log.error("Error occurred while placing order", e);
            return ResponseEntity.internalServerError().body("An error occurred while placing the order: " + e.getMessage());
        }
    		
    }
    @Transactional
    private ResponseEntity<String> placeOrderTransaction() {
    	// save orderDetails and order to database
    	Set<OrderDetail>orderDetails = sessionOrder.getOrderDetails();
		for(OrderDetail orderDetail: orderDetails) {
	        orderDetail.setOrder(sessionOrder);  // Ensure the relationship is set

			orderDetailRepository.save(orderDetail);
		}
		
		sessionOrder.setStatus(OrderStatus.PLACED);
	    sessionOrder.setDate(LocalDateTime.now());  // Set the order date

	    Order savedOrder = orderRepository.save(sessionOrder);

	    sessionOrder.reset();// Clear the session order after successful placement
	    return ResponseEntity.ok("Order " + savedOrder.getId() + " has been placed successfully. Redirecting to order list...");
		
		// Redirect to the listOrder page with a query parameter
		
//		return "redirect:/viewOrderList?message=orderPlaced"; //NEED JAVASCRIPT TO SHOW POP-UP
	
	}

	@GetMapping("/back")
    public String goBack() {
    	return "redirect:/index";
    }
	
}
