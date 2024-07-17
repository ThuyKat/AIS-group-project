package com.AllInSmall.demo.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.AllInSmall.demo.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {

@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
private int id;

@Enumerated(EnumType.STRING) // if not specify, default will be ordinal 0,1,2
private OrderStatus status;

private Date date;

@Column(name="total_Price")
private float totalPrice;

@OneToMany(mappedBy ="order") //name of object Order in OrderDetail
@JsonManagedReference
private Set<OrderDetail> orderDetails;

@ManyToOne
@JoinColumn(name="user_id")
@JsonBackReference
private User user;


public void reset() {
	this.status = null;
	this.orderDetails = new HashSet<>();
	this.totalPrice = 0;
}



}