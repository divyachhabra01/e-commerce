package com.springboot.ecommerce.repository;

import com.springboot.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from product p where p.id In (:products)", nativeQuery = true)
    List<Product> findProductsById(@Param("products") List<Long> products);
}
