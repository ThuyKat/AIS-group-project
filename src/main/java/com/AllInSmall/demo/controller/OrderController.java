package com.AllInSmall.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.AllInSmall.demo.model.Order;
import com.AllInSmall.demo.model.OrderDetail;
import com.AllInSmall.demo.enums.OrderStatus;
import com.AllInSmall.demo.repository.OrderDetailRepository;
import com.AllInSmall.demo.repository.OrderRepository;

@Controller
public class OrderController {
	
    private final Order order; 
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    
    @Autowired
    public OrderController(Order order,OrderRepository orderRepository,OrderDetailRepository orderDetailRepository) {
    	this.order = order;
    	this.orderRepository = orderRepository;
    }
    
    @PostMapping("/placeOrder")
    public String placeOrder(Model model) {
    	
    	// save orderDetails and order to database
		for(OrderDetail orderDetail: order.getOrderDetails()) {
			orderDetailRepository.save(orderDetail);
		}
		
		order.setStatus(OrderStatus.PLACED);
		orderRepository.save(order);
		
		// Redirect to the listOrder page with a query parameter
		return "redirect:/viewOrderList?message=orderPlaced"; //NEED JAVASCRIPT TO SHOW POP-UP
		
    }
    @GetMapping("/back")
    public String goBack() {
    	return "redirect:/index";
    }
	
}
