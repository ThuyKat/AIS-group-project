package com.AllInSmall.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

private String name;

@OneToMany(mappedBy = "category")
@JsonManagedReference
private List<Product>products;


}
