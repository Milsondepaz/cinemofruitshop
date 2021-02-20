package com.cinemo.fruitshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "Cart")
public class Cart {
	
	private static final long SerialVersionUID = 1;
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cart")
	private long id_cart;

	public long getId_cart() {
		return id_cart;
	}

	public void setId_cart(long id_cart) {
		this.id_cart = id_cart;
	}

	public static long getSerialversionuid() {
		return SerialVersionUID;
	} 
	
	
	
	/*
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id_cart_fruits;
	
	@ManyToOne
	@JoinColumn(name = "fruit_id")
	private Fruit fruit;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	private int qtd;

	public Integer getId_cart_fruits() {
		return id_cart_fruits;
	}

	public void setId_cart_fruits(Integer id_cart_fruits) {
		this.id_cart_fruits = id_cart_fruits;
	}

	public Fruit getFruit() {
		return fruit;
	}

	public void setFruit(Fruit fruit) {
		this.fruit = fruit;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
	*/

}
