package com.ecommerce.vn_ecommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long productId;

    private String description;
    private double discount;
    private String image;
    private double price;
    private String productName;
    private int quantity;
    private double special_price;

}
