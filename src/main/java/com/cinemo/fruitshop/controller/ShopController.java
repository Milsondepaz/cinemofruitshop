package com.cinemo.fruitshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cinemo.fruitshop.model.Admin;
import com.cinemo.fruitshop.model.Customer;
import com.cinemo.fruitshop.model.Fruit;
import com.cinemo.fruitshop.repository.AdminRepository;
import com.cinemo.fruitshop.repository.CartRepository;
import com.cinemo.fruitshop.repository.CustomerRepository;
import com.cinemo.fruitshop.repository.FruitRepository;

@Controller
@RequestMapping
public class ShopController {

	@Autowired
	private FruitRepository fruitRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private AdminRepository adminRepository;

	private boolean userIsLogged = false;
	private boolean adminIsLogged = false;

	private String loginCustomerName = "";

	// Main method that calls the main page of the shop fruit
	// This method searches the Database for previously registered fruits and shows
	// the client.
	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView modelAndViewFruit = new ModelAndView("index");
		Iterable<Fruit> fruitList = fruitRepository.findAll();
		modelAndViewFruit.addObject("fruitList", fruitList);
		loginCustomerName = "Login";
		modelAndViewFruit.addObject("loginCustomerName", loginCustomerName);
		return modelAndViewFruit;
		// System.out.println(listaTest.get(i).getImage());
	}

	// Method responsible for sending the user to the Admin area, for this he must
	// first login,
	// If he is already logged in, he will be directed to the admin area directly.
	@GetMapping("/adminarea_adminarealogin")
	public ModelAndView adminArea() {
		ModelAndView modelAndAdminArea = new ModelAndView("admin/adminlogin");
		if (!adminIsLogged) {
			return modelAndAdminArea;
		}
		modelAndAdminArea = new ModelAndView("admin/adminarea");
		return modelAndAdminArea;
	}

	// Checks and validates the Admin credentials to allow access to the area.
	// login button of admin page
	// if the credentials are validated, you will be directed to the area admin,
	// otherwise, keep it on the same page.
	@PostMapping("/adminlogin")
	public ModelAndView adminLogin(@ModelAttribute("admin") Admin pr_admin) {
		Admin admin = adminRepository.findByNameAndPass(pr_admin.getUsername().trim(), pr_admin.getPassword().trim());
		if (admin != null) {
			adminIsLogged = true;
			String username = admin.getUsername();
			ModelAndView modelAndViewAdmin = new ModelAndView("admin/adminarea");
			modelAndViewAdmin.addObject("username", username);
			// message = success in login!
			return modelAndViewAdmin;
		}
		// message = sorry wrong credentials
		// still in login page
		ModelAndView modelAndViewAdmin = new ModelAndView("admin/adminlogin");
		return modelAndViewAdmin;
	}

	/// -------------------------------- customer classes
	/// -----------------------------------------
	// Method responsible to call the customer login page.
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndViewFruitAndLogin = new ModelAndView("index");
		if (userIsLogged == true) {
			userIsLogged = false;
			loginCustomerName = "Login";
			modelAndViewFruitAndLogin.addObject("loginCustomerName", loginCustomerName);
			Iterable<Fruit> fruitList = fruitRepository.findAll();
			modelAndViewFruitAndLogin.addObject("fruitList", fruitList);
			return modelAndViewFruitAndLogin;
		}
		modelAndViewFruitAndLogin = new ModelAndView("customer/customerlogin");
		// System.out.println("passou aqui");
		return modelAndViewFruitAndLogin;
	}

	// bottomlogin
	// take the email and password details to search the database, if there is a
	// user with these credentials,
	// the login is made and the user is redirected to the home page. Otherwise the
	// user will be on the same page.
	@PostMapping("/customerlogin")
	public ModelAndView custemorLogin(@ModelAttribute("customer") Customer sentCustomer) {
		ModelAndView modelAndViewFruitAndCustomer = new ModelAndView("index");
		Customer searchedCustomer = customerRepository.findByEmailAndPassword(sentCustomer.getEmail(),
				sentCustomer.getPassword());
		if (searchedCustomer != null) {
			if (searchedCustomer.getPassword().contentEquals(sentCustomer.getPassword())) {
				Iterable<Fruit> fruitList = fruitRepository.findAll();
				modelAndViewFruitAndCustomer.addObject("fruitList", fruitList);
				modelAndViewFruitAndCustomer.addObject("cumstomer", searchedCustomer.getName());
				loginCustomerName = searchedCustomer.getName() + " - Logout";
				modelAndViewFruitAndCustomer.addObject("loginCustomerName", loginCustomerName);
				userIsLogged = true;
				return modelAndViewFruitAndCustomer;
			}
		}
		modelAndViewFruitAndCustomer = new ModelAndView("customer/customerlogin");
		return modelAndViewFruitAndCustomer;
	}

	// very simple method just to call signup customer page
	@GetMapping("customersignup")
	public String customerSignUp() {
		return "customer/customersignup";
	}

	// bottomregister
	// if the mandatory parameters are filled in,
	// a new user will be added to the Database and will be redirected to the login page ...
	@PostMapping("/customersignup")
	public ModelAndView custemorsignup(@ModelAttribute("customer") Customer newCustomer) {
		ModelAndView modelAndViewFruitAndCustomer = new ModelAndView("customer/signup");		
		
		Customer existentCustomer  = customerRepository.findByEmail(newCustomer.getEmail().trim());		
		
		if (existentCustomer == null) {
			newCustomer.setName(newCustomer.getName().trim());
			newCustomer.setEmail(newCustomer.getEmail().trim());
			customerRepository.save(newCustomer);
			// System.out.println(newCustomer.toString());
			// message: new user sign up sucessfull.
			modelAndViewFruitAndCustomer = new ModelAndView("customer/customerlogin");
		}		
		// message: user with this email e-mail arleady exists,please chose an other
		// e-mail to sign up.
		modelAndViewFruitAndCustomer = new ModelAndView("customer/customerlogin");
		return modelAndViewFruitAndCustomer;
	}

	@PostMapping("/")
	public ModelAndView searchFruit(@ModelAttribute("fruit") Fruit fruit) {			
		ModelAndView modelAndViewFruit = new ModelAndView("index");		
		Iterable<Fruit> fruitList = fruitRepository.findByName(fruit.getName());		
		modelAndViewFruit.addObject("fruitList", fruitList);				
		return modelAndViewFruit;
	}

}