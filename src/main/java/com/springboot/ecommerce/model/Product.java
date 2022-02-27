package com.springboot.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Integer id;
    @Column(name = "product_title")
    private String title;
    @Column(name = "product_description")
    private String description;

    @OneToMany(mappedBy = "product",  fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderProductMap> orderProductMap;
}
