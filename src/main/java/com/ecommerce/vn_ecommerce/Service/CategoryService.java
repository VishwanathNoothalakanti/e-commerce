package com.ecommerce.vn_ecommerce.Service;

import com.ecommerce.vn_ecommerce.DTO.CategoryDTO;
import com.ecommerce.vn_ecommerce.DTO.CategoryResponse;
import com.ecommerce.vn_ecommerce.Entity.Category;

public interface CategoryService {

    CategoryResponse getCategories();

    CategoryDTO addCategory(Category category);

    Category getCategoryById(Long id);

    void deleteCategory(Long id);

    Category updateCategory(Long id, Category updatedCategory);
}
