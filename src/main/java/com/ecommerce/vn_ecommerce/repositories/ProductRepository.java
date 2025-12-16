package com.ecommerce.vn_ecommerce.repositories;

import com.ecommerce.vn_ecommerce.Entity.Category;
import com.ecommerce.vn_ecommerce.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findByCategory(Category category);

    List<Products> findByproductNameLikeIgnoreCase(String keyword);
}
