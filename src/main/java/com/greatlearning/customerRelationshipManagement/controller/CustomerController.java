package com.greatlearning.customerRelationshipManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.customerRelationshipManagement.model.Customer;
import com.greatlearning.customerRelationshipManagement.service.CustomerService;


@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@RequestMapping("/list")
	public String listCustomers(Model theModel) {

		List<Customer> theCustomers = customerService.findAll();

		theModel.addAttribute("Customers", theCustomers);

		return "list-Students";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Customer theCustomer = new Customer();

		theModel.addAttribute("Customer", theCustomer);

		return "Customer-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
			Model theModel) {

		Customer theCustomer = customerService.findById(theId);

		theModel.addAttribute("Customer", theCustomer);

		return "Customer-form";			
	}
	
	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id,
			@RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname,@RequestParam("email") String email) {

		System.out.println(id);
		Customer theCustomer;
		if(id!=0)
		{
			theCustomer = customerService.findById(id);
			theCustomer.setFirstname(firstname);
			theCustomer.setLastname(lastname);
			theCustomer.setEmail(email);
		}
		else
			theCustomer=new Customer(firstname, lastname, email);
			customerService.save(theCustomer);
			return "redirect:/customers/list";

	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {

		
		customerService.deleteById(theId);

		return "redirect:/customers/list";

	}


}
