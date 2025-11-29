package com.ecommerce.vn_ecommerce.Service;

import com.ecommerce.vn_ecommerce.DTO.CategoryDTO;
import com.ecommerce.vn_ecommerce.DTO.CategoryResponse;
import com.ecommerce.vn_ecommerce.Entity.Category;

public interface CategoryService {

    CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortByCategoryId, String sortDir);

    CategoryDTO addCategory(Category category);

    CategoryDTO getCategoryById(Long id);

    void deleteCategory(Long id);

    CategoryDTO updateCategory(Long id, Category updatedCategory);
}
