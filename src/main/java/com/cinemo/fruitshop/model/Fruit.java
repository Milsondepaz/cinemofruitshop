package com.cinemo.fruitshop.model;

import javax.persistence.*;


@Entity
@Table (name = "Fruit")
public class Fruit {
	
	private static final long SerialVersionUID = 1;
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_fruit")
	private long id_fruit;    
    
    @Column(name = "name")
	private String name;
    
    @Column(name = "image_path")
	private String image_path;
    
    @Column(name = "price")
	private float price;
    
    @Column(name = "quantity")
	private int quantity;
	
	public Fruit ( ) {
		
	}

	public long getId_fruit() {
		return id_fruit;
	}

	public void setId_fruit(long id_fruit) {
		this.id_fruit = id_fruit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public static long getSerialversionuid() {
		return SerialVersionUID;
	}

	@Override
	public String toString() {
		return "Fruit [id_fruit=" + id_fruit + ", name=" + name + ", image_path=" + image_path + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
	
	
	
	
	

}
