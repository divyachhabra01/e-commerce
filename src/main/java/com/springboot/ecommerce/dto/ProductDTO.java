package com.springboot.ecommerce.dto;

import com.springboot.ecommerce.model.OrderProductMap;
import lombok.Data;

import java.util.List;
@Data
public class ProductDTO {
    private Integer id;
    private String title;
    private String description;
    private List<OrderProductMap> orderProductMap;
}
