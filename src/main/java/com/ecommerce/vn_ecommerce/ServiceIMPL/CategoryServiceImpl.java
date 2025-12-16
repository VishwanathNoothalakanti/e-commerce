package com.ecommerce.vn_ecommerce.ServiceIMPL;

import com.ecommerce.vn_ecommerce.DTO.CategoryDTO;
import com.ecommerce.vn_ecommerce.DTO.CategoryResponse;
import com.ecommerce.vn_ecommerce.Entity.Category;
import com.ecommerce.vn_ecommerce.Service.CategoryService;
import com.ecommerce.vn_ecommerce.repositories.CategoryRespository;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.*;
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
    public CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortByCategoryId, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortByCategoryId).ascending() : Sort.by(sortByCategoryId).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Category> categoryPage = categoryRespository.findAll(pageable);

        List<Category> categories = categoryPage.getContent();
        if (categories.isEmpty()) {
            System.out.println("Couldn't find any Categories");
        }
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getNumberOfElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());

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
        c.setCategoryName(updatedCategory.getCategoryName());
        Category updatedcategory = categoryRespository.save(c);

        return modelMapper.map(updatedCategory, CategoryDTO.class);
    }


}
