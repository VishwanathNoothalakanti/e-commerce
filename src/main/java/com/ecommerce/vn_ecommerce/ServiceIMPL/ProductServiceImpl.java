package com.ecommerce.vn_ecommerce.ServiceIMPL;

import com.ecommerce.vn_ecommerce.DTO.ProductDTO;
import com.ecommerce.vn_ecommerce.DTO.ProductResponse;
import com.ecommerce.vn_ecommerce.Entity.Category;
import com.ecommerce.vn_ecommerce.Entity.Products;
import com.ecommerce.vn_ecommerce.Service.ProductService;
import com.ecommerce.vn_ecommerce.repositories.CategoryRespository;
import com.ecommerce.vn_ecommerce.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRespository categoryRespository;

    @Autowired
    ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO addProduct(Products products, Long categoryId) {
       Category category = categoryRespository.findById(categoryId)
               .orElseThrow(() ->
                       new RuntimeException("No category found"));
       products.setCategory(category);
       products.setDescription(products.getDescription());
       products.setDiscount(products.getDiscount());
       products.setImage(products.getImage());
       products.setPrice(products.getPrice());
       products.setProductName(products.getProductName());
       products.setQuantity(products.getQuantity());
       double specialPrice = products.getPrice() - ((products.getDiscount() * 0.01) * products.getPrice());
       products.setSpecial_price(specialPrice);

       Products savedProduct = productRepository.save(products);
       return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductResponse getProducts(Integer pageNumber, Integer pageSize, String sortByProductId, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortByProductId).ascending() : Sort.by(sortByProductId).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Products> productsPage = productRepository.findAll(pageable);

        List<Products> products = productsPage.getContent();
        if (products.isEmpty()) {
            System.out.println("Couldn't find any product");
        }

       List<ProductDTO> productDTOS = products.stream().map(products1 ->
                        modelMapper.map(products1, ProductDTO.class))
               .collect(Collectors.toList());

       ProductResponse productResponse = new ProductResponse();
       productResponse.setContent(productDTOS);
       productResponse.setPageNumber(productsPage.getNumber());
       productResponse.setPageSize(productsPage.getSize());
       productResponse.setPageSize(productsPage.getSize());
       productResponse.setTotalElements(productsPage.getNumberOfElements());
       productResponse.setTotalPages(productsPage.getTotalPages());
       productResponse.setLastPage(productsPage.isLast());

       return productResponse;
    }

    @Override
    public ProductResponse getProductByCategory(Long categoryId) {
        Category category = categoryRespository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException("No Category Found!"));

        List<Products> products = productRepository.findByCategory(category);

        List<ProductDTO> productDTOS = products.stream().map(products1 -> modelMapper.map(
                products1, ProductDTO.class)).collect(Collectors.toList());

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);

        return productResponse;

    }

    @Override
    public ProductResponse searchProductsByKeyword(String keyword) {
        List<Products> products = productRepository.findByproductNameLikeIgnoreCase("%" + keyword + "%");
        List<ProductDTO> productDTOS = products.stream().map(products1 ->
                modelMapper.map(products1, ProductDTO.class))
                .collect(Collectors.toList());

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);

        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(Products products, Long productId) {
        Products productDTO = productRepository.findById(productId).orElseThrow(()
                -> new RuntimeException("No product found"));

        productDTO.setProductName(products.getProductName());

        Products updatedProductName = productRepository.save(productDTO);

       return modelMapper.map(updatedProductName, ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        Products products = productRepository.findById(productId).orElseThrow(()
                -> new RuntimeException("No product found"));

        productRepository.delete(products);

        return modelMapper.map(products, ProductDTO.class);
    }
}
