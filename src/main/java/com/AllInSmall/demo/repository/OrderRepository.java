package com.AllInSmall.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AllInSmall.demo.model.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{

}
