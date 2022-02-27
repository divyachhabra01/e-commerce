package com.springboot.ecommerce.repository;

import com.springboot.ecommerce.model.OrderProductMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderProductMapRepository extends JpaRepository<OrderProductMap, Integer> {

    @Query(value = "select * from order_product_map",nativeQuery = true)
    public List<OrderProductMap>findAllOrderProductMapList();
}