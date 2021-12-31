package com.greatlearning.customerRelationshipManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.customerRelationshipManagement.model.Customer;

@Service
public interface CustomerService {
	
	public List<Customer> findAll();
	
	public Customer findById(int theId);
	
	public void save(Customer theCustomer);

	public void deleteById(int theId);

}
