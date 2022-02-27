package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.dto.ProductDTO;
import com.springboot.ecommerce.model.Product;
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
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ECommerceService  eCommerceService;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createProduct(@RequestBody ProductDTO productDTO) {
    final  Product product=modelMapper.map(productDTO,Product.class);
       return eCommerceService.createProduct(product);
    }

}
