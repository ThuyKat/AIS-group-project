package com.AllInSmall.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail  {

@EmbeddedId
OrderDetailKey id;

@ManyToOne
@JsonBackReference
@MapsId("productId") // attribute of OrderDetailKey
@JoinColumn(name="product_id")
private Product product;

@ManyToOne
@JsonBackReference
@MapsId("orderId") // attribute of OrderDetailKey
@JoinColumn(name="order_id")
private Order order;

private int quantity;




}
