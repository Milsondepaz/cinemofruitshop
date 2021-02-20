package com.cinemo.fruitshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinemo.fruitshop.model.Cart;


public interface CartRepository extends JpaRepository<Cart, Long> {

	
}


