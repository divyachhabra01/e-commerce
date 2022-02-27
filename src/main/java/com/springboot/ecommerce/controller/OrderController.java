package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.dto.OrderDetailsDTO;
import com.springboot.ecommerce.model.*;
import com.springboot.ecommerce.service.ECommerceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private ECommerceService eCommerceService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllOrders() {
        return eCommerceService.getAllOrders();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> placeOrder(@RequestParam("customerId") Integer customerId, @RequestParam("status") String status, @RequestBody List<Long> productIds) {
        eCommerceService.placeOrder(customerId, Enum.valueOf(Status.class, status), productIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> updateStatus(@RequestBody OrderDetailsDTO orderDetailsDTO, @PathVariable("id") Integer id) {
        final OrderDetails orderDetails=modelMapper.map(orderDetailsDTO,OrderDetails.class);
        return eCommerceService.updateStatus(orderDetails, id);
    }

}
