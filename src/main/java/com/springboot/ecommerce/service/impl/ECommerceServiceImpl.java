package com.springboot.ecommerce.service.impl;

import com.springboot.ecommerce.exception.ResourceNotFoundException;
import com.springboot.ecommerce.model.*;
import com.springboot.ecommerce.repository.CustomerRepository;
import com.springboot.ecommerce.repository.OrderProductMapRepository;
import com.springboot.ecommerce.repository.OrderRepository;
import com.springboot.ecommerce.repository.ProductRepository;
import com.springboot.ecommerce.service.ECommerceService;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ECommerceServiceImpl implements ECommerceService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderProductMapRepository orderProductMapRepository;

    @Override
    public ResponseEntity createCustomer(Customer customer) {
        try {
            customerRepository.saveAndFlush(customer);
        } catch (Exception e) {
            log.error("Getting Internal Server Error due to {} ", Arrays.toString(e.getStackTrace()));
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity createProduct(Product product) {
        try {
            productRepository.saveAndFlush(product);
        } catch (Exception e) {
            log.error("Getting Internal Server Error due to {} ", Arrays.toString(e.getStackTrace()));
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orderResponses = new ArrayList<>();
        try {
            List<OrderProductMap> list = orderProductMapRepository.findAll();
            for (OrderProductMap orderProductMap : list) {
                OrderResponse orderResponse = new OrderResponse();
                orderResponse.setOrder_id(orderProductMap.getId());
                orderResponse.setCustomer(orderProductMap.getOrderDetail().getCustomer().getName());
                orderResponse.setAddress(orderProductMap.getOrderDetail().getCustomer().getAddress());
                orderResponse.setCountry(orderProductMap.getOrderDetail().getCustomer().getCountry());
                orderResponse.setStatus(orderProductMap.getOrderDetail().getStatus());
                orderResponse.setProduct_description(orderProductMap.getProduct().getDescription());
                orderResponse.setProduct_title(orderProductMap.getProduct().getTitle());
                orderResponse.setDate(orderProductMap.getOrderDetail().getDate());
                orderResponses.add(orderResponse);
            }
        } catch (Exception e) {
            log.error("Getting Internal Server Error due to {} ", Arrays.toString(e.getStackTrace()));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        if (orderResponses.size() == 0) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderResponses,HttpStatus.OK);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> placeOrder(Integer customerId, Status status, List<Long> productIds) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(ResourceNotFoundException.builder().message("Could not find any customer for this id: " + customerId)::build);
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCustomer(customer);
        orderDetails.setStatus(status);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        orderDetails.setDate(formatter.format(new Date()));
        try {
            orderRepository.saveAndFlush(orderDetails);
            List<Product> products = productRepository.findProductsById(productIds);
            List<OrderProductMap> orderProductMapList = new ArrayList<>();
            products.forEach(product -> {
                OrderProductMap orderProductMap = new OrderProductMap();
                orderProductMap.setOrderDetail(orderDetails);
                orderProductMap.setProduct(product);
                orderProductMapList.add(orderProductMap);
            });

            orderProductMapRepository.saveAll(orderProductMapList);
        } catch (Exception e) {
            log.error("Getting Internal Server Error due to {} ", Arrays.toString(e.getStackTrace()));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateStatus(OrderDetails orderDetails, Integer id) {
        OrderDetails oldOrder = orderRepository.findById(id).orElseThrow(ResourceNotFoundException.builder().message("Could not found any order for this id:" + id)::build);
        oldOrder.setStatus(orderDetails.getStatus());
        final OrderDetails updatedOrder = orderRepository.save(oldOrder);
        return ResponseEntity.ok().body(updatedOrder);
    }
}
