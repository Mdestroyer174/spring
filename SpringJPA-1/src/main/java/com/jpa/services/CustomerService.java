package com.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.entities.Customer;
import com.jpa.repository.CustomerRepository;

@Service
public class CustomerService {
@Autowired
CustomerRepository customerRepository;

public List<Customer> findAllCustomers(){
	return customerRepository.findAll();
}
public Customer findById(Long id) {
    return customerRepository.findById(id).orElse(null);
}

public Customer save(Customer customer) {
    return customerRepository.save(customer);
}

public void deleteById(Long id) {
    customerRepository.deleteById(id);
}
}
