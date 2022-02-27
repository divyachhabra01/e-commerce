package com.springboot.ecommerce.repository;

import com.springboot.ecommerce.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetails, Integer> {


}
