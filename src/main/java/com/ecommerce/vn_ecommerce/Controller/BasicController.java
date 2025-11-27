package com.ecommerce.vn_ecommerce.Controller;

import com.ecommerce.vn_ecommerce.DTO.CategoryDTO;
import com.ecommerce.vn_ecommerce.DTO.CategoryResponse;
import com.ecommerce.vn_ecommerce.Entity.Category;
import com.ecommerce.vn_ecommerce.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BasicController {

    @Autowired
    private CategoryService categoryService;

    public BasicController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<CategoryResponse> getCategories() {
        CategoryResponse categoryResponse =  categoryService.getCategories();
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping("/createCategory")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody Category category) {
       CategoryDTO createdCategory = categoryService.addCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/getCategory/{id}")
    public ResponseEntity<String> getCategoryById(@PathVariable Long id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category.getName(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        return new ResponseEntity<>(categoryService.updateCategory(id, updatedCategory), HttpStatus.CREATED);
    }


}
