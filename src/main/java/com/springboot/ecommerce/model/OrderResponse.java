package com.springboot.ecommerce.model;

import lombok.Data;

@Data
public class OrderResponse {
    private Integer order_id;
    private String customer;
    private String address;
    private String country;
    private String product_title;
    private String product_description;
    private Status status;
    private String date;

}
