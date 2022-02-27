package com.springboot.ecommerce.dto;

import com.springboot.ecommerce.model.OrderDetails;
import lombok.Data;

import java.util.List;
@Data
public class CustomerDTO {
    private Integer id;
    private String name;
    private String country;
    private String address;
    private List<OrderDetails> orders;

}
