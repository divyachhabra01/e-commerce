package com.springboot.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity

// This class represents all the products in a order
public class OrderProductMap {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    @JsonIgnore
    private Integer id;

    @OneToOne
    private OrderDetails orderDetail;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;


}
