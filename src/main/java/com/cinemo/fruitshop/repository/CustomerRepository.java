package com.cinemo.fruitshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cinemo.fruitshop.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query(value = "select * from customer c where c.email = ?1 and c.password = ?2", nativeQuery = true)
	Customer findByEmailAndPassword(String email, String password);
	
	Customer findByEmail(String Email);
	
	//@Query(value = "select * from users u where u.name = ?1 and u.user_type =?1", nativeQuery = true)
	//User findAdminByUserName(String userName);
}