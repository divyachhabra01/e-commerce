package com.springboot.ecommerce.service;

import com.springboot.ecommerce.model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ECommerceService {
    ResponseEntity createCustomer(Customer customer);
    ResponseEntity createProduct(Product product);
    ResponseEntity<List<OrderResponse>> getAllOrders();
    ResponseEntity<?> placeOrder(Integer customerId, Status status, List<Long> productIds) ;
    ResponseEntity<?> updateStatus(OrderDetails orderDetails,Integer id);

}
