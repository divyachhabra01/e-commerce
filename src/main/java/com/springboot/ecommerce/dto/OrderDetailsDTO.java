package com.springboot.ecommerce.dto;

import com.springboot.ecommerce.model.Customer;
import com.springboot.ecommerce.model.Status;
import lombok.Data;

@Data
public class OrderDetailsDTO {
    private Integer id;
    private Status status;
    private Customer customer;
    private String date;
}
