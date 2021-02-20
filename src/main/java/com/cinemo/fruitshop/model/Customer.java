package com.cinemo.fruitshop.model;

import javax.persistence.*;

@Entity
@Table (name = "Customer")
public class Customer {
	
	private static final long SerialVersionUID = 1;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_customer")
	private long id_customer;    
    
    @Column(name = "name")      
	private String name;
    
    @Column(name = "email")
	private String email;
    
    @Column(name = "password")
	private String password;    

	public long getId_customer() {
		return id_customer;
	}

	public void setId_customer(long id_customer) {
		this.id_customer = id_customer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public static long getSerialversionuid() {
		return SerialVersionUID;
	}

	@Override
	public String toString() {
		return "Customer [id_customer=" + id_customer + ", name=" + name + ", email=" + email + ", password=" + password
				+ "]";
	}    
	
	

}
