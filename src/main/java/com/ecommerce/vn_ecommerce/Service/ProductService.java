package com.ecommerce.vn_ecommerce.Service;

import com.ecommerce.vn_ecommerce.DTO.ProductDTO;
import com.ecommerce.vn_ecommerce.DTO.ProductResponse;
import com.ecommerce.vn_ecommerce.Entity.Products;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    ProductDTO addProduct(Products products, Long categoryId);

    ProductResponse getProducts(Integer pageNumber, Integer pageSize, String sortByProductId, String sortDir);

    ProductResponse getProductByCategory(Long categoryId);

    ProductResponse searchProductsByKeyword(String keyword);

    ProductDTO updateProduct(Products products, Long productId);

    ProductDTO deleteProduct(Long productId);

}
