package com.ecommerce.vn_ecommerce.ServiceIMPL;

import com.ecommerce.vn_ecommerce.DTO.CategoryDTO;
import com.ecommerce.vn_ecommerce.DTO.CategoryResponse;
import com.ecommerce.vn_ecommerce.Entity.Category;
import com.ecommerce.vn_ecommerce.Service.CategoryService;
import com.ecommerce.vn_ecommerce.repositories.CategoryRespository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRespository categoryRespository;

    @Autowired
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRespository categoryRespository, ModelMapper modelMapper) {
        this.categoryRespository = categoryRespository;
        this.modelMapper = modelMapper;
    }

   // private final ArrayList<Category> categories = new ArrayList<>();

    @Override
    public CategoryResponse getCategories() {
        List<Category> categories = categoryRespository.findAll();
        if (categories.isEmpty()) {
            System.out.println("Couldn't find any Categories");
        }
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);

        return categoryResponse;
    }

    @Override
    public CategoryDTO addCategory(Category category) {
       Category category1 =  categoryRespository.save(category);

        return modelMapper.map(category1, CategoryDTO.class);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category categoryById =  categoryRespository.getReferenceById(id);

        return modelMapper.map(categoryById, CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRespository.deleteById(id);
    }

    @Override
    public CategoryDTO updateCategory(Long id, Category updatedCategory) {
        Category c = categoryRespository.getReferenceById(id);
        c.setName(updatedCategory.getName());
        Category updatedcategory = categoryRespository.save(c);

        return modelMapper.map(updatedCategory, CategoryDTO.class);
    }


}
