package com.ecommerce.vn_ecommerce.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String description;
    private double discount;
    private String image;
    private double price;
    private String productName;
    private int quantity;
    private double special_price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private long seller_id;

}
