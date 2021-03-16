package com.cinemo.fruitshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cinemo.fruitshop.model.Admin;
import com.cinemo.fruitshop.model.Customer;
import com.cinemo.fruitshop.model.Fruit;
import com.cinemo.fruitshop.repository.AdminRepository;
import com.cinemo.fruitshop.repository.CartRepository;
import com.cinemo.fruitshop.repository.CustomerRepository;
import com.cinemo.fruitshop.repository.FruitRepository;
import java.util.Optional;

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

    private boolean adminIsLogged = false;
    private boolean customerIsLogged = false;

    private String customerLoggedName = "Login";

    private Customer loggedCustomer;
    private Admin loggedAdmin;

    /*
	 * public Admin searchAdmin(String username, String passowrd) { if
	 * (username.contentEquals("milsona") && passowrd.contentEquals("Bola10xut")) {
	 * return new Admin(1, "milsona", "milsona@gmail.com", "Job Seeker", "milsona");
	 * } else if (username.contentEquals("cinemo") &&
	 * passowrd.contentEquals("cinemo")) { return new Admin(2, "cinemo",
	 * "milsona@gmail.com", "Job Owner", "cinemo2021"); } return null; }
     */
    // Main method that calls the main page of the shop fruit
    // This method searches the Database for previously registered fruits and shows
    // the client.
    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndViewFruit = new ModelAndView("index");
        Iterable<Fruit> fruitList = fruitRepository.findAll();
        modelAndViewFruit.addObject("fruitList", fruitList);
        if (customerIsLogged == true) {
            customerLoggedName = loggedCustomer.getName() + " - Logout";
        } else {
            customerLoggedName = "Login";
        }
        modelAndViewFruit.addObject("customerLoggedName", customerLoggedName);

        return modelAndViewFruit;
        // System.out.println(listaTest.get(i).getImage());
    }

    // Method responsible for sending the user to the Admin area, for this he must
    // first login,
    // If he is already logged in, he will be directed to the admin area directly.
    @GetMapping("/adminarea_adminarealogin")
    public ModelAndView adminArea() {
        ModelAndView modelAndAdminArea = new ModelAndView("admin/adminlogin");
        if (adminIsLogged == false) {
            return modelAndAdminArea;
        }
        String username = loggedAdmin.getUsername();
        modelAndAdminArea = new ModelAndView("admin/adminarea");
        modelAndAdminArea.addObject("username", username);
        customerIsLogged = false;
        return modelAndAdminArea;
    }

    // Checks and validates the Admin credentials to allow access to the area.
    // login button of admin page
    // if the credentials are validated, you will be directed to the area admin,
    // otherwise, keep it on the same page.
    @PostMapping("/adminlogin")
    public ModelAndView adminLogin(@ModelAttribute("admin") Admin pr_admin) {
        ModelAndView modelAndViewAdmin = new ModelAndView("admin/adminarea");
        Admin admin = adminRepository.findByNameAndPass(pr_admin.getUsername().trim(), pr_admin.getPassword().trim());
        if (customerIsLogged == true) {
            if (admin != null) {
                adminIsLogged = true;
                customerIsLogged = false;
                loggedAdmin = admin;
                String username = loggedAdmin.getUsername();
                modelAndViewAdmin.addObject("username", username);
                // message = success in login!
                return modelAndViewAdmin;
            }
        } else {

            if (admin != null) {
                adminIsLogged = true;
                customerIsLogged = false;
                loggedAdmin = admin;
                String username = loggedAdmin.getUsername();
                modelAndViewAdmin.addObject("username", username);
                // message = success in login!
                return modelAndViewAdmin;
            }
        }
        // message = sorry wrong credentials
        // still in login page
        modelAndViewAdmin = new ModelAndView("admin/adminlogin");
        return modelAndViewAdmin;
    }

    // Method responsible for admin logout
    @GetMapping("/adminlogout")
    public ModelAndView adminLogout() {
        adminIsLogged = false;
        return home();
    }

    /// -------------------------------- customer classes
    /// -----------------------------------------
    // Method responsible to call the customer login page.
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndViewFruitAndLogin = new ModelAndView("index");
        if (customerIsLogged == true) {
            customerIsLogged = false;
            customerLoggedName = "Login";
            modelAndViewFruitAndLogin.addObject("customerLoggedName", customerLoggedName);
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
                customerLoggedName = searchedCustomer.getName() + " - Logout";
                modelAndViewFruitAndCustomer.addObject("customerLoggedName", customerLoggedName);
                loggedCustomer = searchedCustomer;
                customerLoggedName = loggedCustomer.getName() + " - Logout";
                customerIsLogged = true;
                adminIsLogged = false;
                customerLoggedName = searchedCustomer.getName();
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
    // a new user will be added to the Database and will be redirected to the login
    // page ...
    @PostMapping("/customersignup")
    public ModelAndView custemorsignup(@ModelAttribute("customer") Customer newCustomer) {
        ModelAndView modelAndViewFruitAndCustomer = new ModelAndView("customer/signup");
        Customer existentCustomer = customerRepository.findByEmail(newCustomer.getEmail().trim());
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
        if (customerIsLogged == true) {
            customerLoggedName = loggedCustomer.getName() + " - Logout";
        } else {
            customerLoggedName = "Login";
        }
        modelAndViewFruit.addObject("customerLoggedName", customerLoggedName);
        modelAndViewFruit.addObject("fruitList", fruitList);
        return modelAndViewFruit;
    }

    //
    @GetMapping("adminareacustomers")
    public ModelAndView adminCustomer() {
        ModelAndView modelAndViewAdminareaCustomers = new ModelAndView("admin/customers");
        if (adminIsLogged == true) {
            Iterable<Customer> customerList = customerRepository.findAll();

            modelAndViewAdminareaCustomers.addObject("username", loggedAdmin.getUsername());

            modelAndViewAdminareaCustomers.addObject("customerList", customerList);
            return modelAndViewAdminareaCustomers;
        }
        modelAndViewAdminareaCustomers = new ModelAndView("admin/adminlogin");
        return modelAndViewAdminareaCustomers;
    }

    @GetMapping("fruits")
    public ModelAndView adminFruits() {
        ModelAndView modelAndViewAdminareaCustomers = new ModelAndView("admin/fruits");

        if (adminIsLogged == true) {
            Iterable<Customer> customerList = customerRepository.findAll();
            Iterable<Fruit> fruitList = fruitRepository.findAll();

            modelAndViewAdminareaCustomers.addObject("fruitList", fruitList);

            modelAndViewAdminareaCustomers.addObject("username", loggedAdmin.getUsername());

            modelAndViewAdminareaCustomers.addObject("customerList", customerList);

            //Fruit fruit = new Fruit();
            //modelAndViewAdminareaCustomers.addObject("fruit", fruit);
            return modelAndViewAdminareaCustomers;
        }

        modelAndViewAdminareaCustomers = new ModelAndView("admin/adminlogin");

        return modelAndViewAdminareaCustomers;
    }

    @RequestMapping(path = "editcustomer/{id_customer}")
    public ModelAndView editCustomer(@PathVariable("id_customer") long id_customer) {
        Customer customer = customerRepository.findById(id_customer);
        ModelAndView editCustomer = new ModelAndView("admin/editcustomer");
        editCustomer.addObject("username", loggedAdmin.getUsername());
        editCustomer.addObject("customer", customer);
        return editCustomer;
    }

    @RequestMapping(path = "/updatecustomer", method = RequestMethod.POST)
    public ModelAndView updateCustomer(Customer pr_customer) {
        // Optional<Customer> customer =
        // customerRepository.findByIdOptional(pr_customer.getId_customer());
        // if (customer.isPresent()) {
        // }
        Customer newCustomer = pr_customer;
        newCustomer.setEmail(pr_customer.getEmail());
        newCustomer.setName(pr_customer.getName());
        newCustomer.setPassword(pr_customer.getPassword());
        newCustomer = customerRepository.save(newCustomer);
        return adminCustomer();
    }

    @RequestMapping(path = "deletecustomer/{id_customer}")
    public ModelAndView deleteCustomer(@PathVariable("id_customer") long id_customer) {
        // Optional<Customer> customer =
        // customerRepository.findByIdOptional(id_customer);
        customerRepository.deleteById(id_customer);
        return adminCustomer();
        // if (customer.isPresent()) {
        // }
    }

    // add produts - Fruits
    // if the mandatory parameters are filled in,
    // a new user will be added to the Database and will be redirected to the login
    // page ...
    @PostMapping("/addnewfruit")
    public ModelAndView addNewFruit(@ModelAttribute("fruit") Fruit fruit) {

        fruitRepository.save(fruit);


        return adminFruits();
    }

    @RequestMapping(path = "deletefruit/{id_fruit}")
    public ModelAndView deleteFruit(@PathVariable("id_fruit") long id_fruit) {
        fruitRepository.deleteById(id_fruit);
        return adminFruits();
    }

    // call edit fruit modal
    // @RequestMapping(path = "editfruit/{id_fruit}")
    @RequestMapping(path = "editfruit/{id_fruit}")
    public ModelAndView editfruit(@PathVariable("id_fruit") long id_fruit) {
        Fruit fruit = fruitRepository.findById(id_fruit);
        //System.out.println(fruit.toString());
        ModelAndView modelAndViewAdminareaCustomers = new ModelAndView("admin/editfruit");
        modelAndViewAdminareaCustomers.addObject("username", loggedAdmin.getUsername());
        modelAndViewAdminareaCustomers.addObject("fruit", fruit);
        return modelAndViewAdminareaCustomers;
    }

    @RequestMapping(path = "/updatefruit", method = RequestMethod.POST)
    public ModelAndView updateFruit(Fruit pr_fruit) {
        Fruit newFruit = pr_fruit;
        newFruit.setName(pr_fruit.getName());
        newFruit.setPrice(pr_fruit.getPrice());
        newFruit.setQuantity(pr_fruit.getQuantity());
        newFruit.setImage_path(pr_fruit.getImage_path());
        fruitRepository.save(newFruit);
        return adminFruits();
    }

}
