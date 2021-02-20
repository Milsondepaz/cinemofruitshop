package com.cinemo.fruitshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cinemo.fruitshop.model.Fruit;

public interface FruitRepository extends JpaRepository<Fruit, Long> {

	Fruit findById(long id);
	List<Fruit> findAll();	
	
	
	@Query(value = "select * from fruit f where f.name like %:name%", nativeQuery = true)
	List<Fruit> findByName(@Param("name") String name);
	
	//@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	//List<Movie> searchByTitleLike(@Param("title") String title);

}


