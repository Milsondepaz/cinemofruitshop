package com.cinemo.fruitshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")
public class Admin {

	private static final long SerialVersionUID = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_admin")
	private long id_admin;

	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;
	
	//private String role;

	@Column(name = "password")
	private String password;
	
	
	/*
	 * public Admin(long id_admin, String username, String email, String role,
	 * String password) { this.id_admin = id_admin; this.email = email;
	 * this.username = username; this.password = password; this.role = role;
	 * 
	 * }
	 */

	public long getId_admin() {
		return id_admin;
	}

	public void setId_admin(long id_admin) {
		this.id_admin = id_admin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	/*
	 * public String getRole() { return role; }
	 * 
	 * public void setRole(String role) { this.role = role; }
	 */

	@Override
	public String toString() {
		return "Admin [id_admin=" + id_admin + ", username=" + username + ", email=" + email + ", password=" + password
				+ "]";
	}

}
