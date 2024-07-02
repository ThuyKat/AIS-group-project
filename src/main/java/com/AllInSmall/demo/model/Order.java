package com.AllInSmall.demo.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
private List<OrderDetail> orderDetails;

@ManyToOne
@JoinColumn(name="user_id")
@JsonBackReference
private User user;


public void reset() {
	this.status = null;
	this.orderDetails = new ArrayList<>();
	this.totalPrice = 0;
}
}