package com.cinemo.fruitshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cinemo.fruitshop.model.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long> {

	@Query(value = "select * from admin a where a.username = ?1 and a.password =?2", nativeQuery = true)
	Admin findByNameAndPass(String username, String password);
}
