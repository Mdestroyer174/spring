package com.jpa.entities.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.entities.Customer;
import com.jpa.services.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Obtener todos los clientes
    @GetMapping("/view")
    public List<Customer> getCustomers() {
        log.info("Fetching all customers");
        return customerService.findAllCustomers();
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo cliente
    @PostMapping("/add")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    // Actualizar un cliente
    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            customer.setName(customerDetails.getName());
            customer.setSurname(customerDetails.getSurname());
            customer.setBirtdate(customerDetails.getBirtdate());
            customer.setPhone(customerDetails.getPhone());
            customer.setCountry(customerDetails.getCountry());
            customer.setCity(customerDetails.getCity());
            customer.setDirection(customerDetails.getDirection());
            customer.setPostcode(customerDetails.getPostcode());
            return new ResponseEntity<>(customerService.save(customer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un cliente
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            customerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

