package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.dto.CustomerDTO;
import com.springboot.ecommerce.model.Customer;
import com.springboot.ecommerce.service.ECommerceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private ECommerceService eCommerceService;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCustomer(@RequestBody CustomerDTO customerDTO) {
   final Customer customer=modelMapper.map(customerDTO,Customer.class);
       return eCommerceService.createCustomer(customer);
    }
}
