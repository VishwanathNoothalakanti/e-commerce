package com.ecommerce.vn_ecommerce.Controller;

import com.ecommerce.vn_ecommerce.AppConstants.AppConstants;
import com.ecommerce.vn_ecommerce.DTO.CategoryDTO;
import com.ecommerce.vn_ecommerce.DTO.ProductDTO;
import com.ecommerce.vn_ecommerce.DTO.ProductResponse;
import com.ecommerce.vn_ecommerce.Entity.Category;
import com.ecommerce.vn_ecommerce.Entity.Products;
import com.ecommerce.vn_ecommerce.Service.CategoryService;
import com.ecommerce.vn_ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping("/api/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody Products products, @PathVariable Long categoryId) {

       ProductDTO productDTO =  productService.addProduct(products, categoryId);

       return new ResponseEntity<>(productDTO,HttpStatus.CREATED);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "sortByProductId", defaultValue = AppConstants.SORT_BY_Product_ID) String sortByCategoryId,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.SORT_DIR) String sortDir
    ) {
        ProductResponse productDTO = productService.getProducts(pageNumber, pageSize, sortByCategoryId, sortDir);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping("/api/public/categories/{categoryId}/product")
    public ResponseEntity<ProductResponse> getProductByCategory(@PathVariable Long categoryId){

        ProductResponse productResponse = productService.getProductByCategory(categoryId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/api/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductsByKeyword(@PathVariable String keyword) {
        ProductResponse productResponse = productService.searchProductsByKeyword(keyword);
        return new ResponseEntity<>(productResponse, HttpStatus.FOUND);
    }

    @PutMapping("/api/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody Products products, @PathVariable Long productId) {
        ProductDTO productDTO = productService.updateProduct(products, productId);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @DeleteMapping("admin/product/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId) {
       ProductDTO productDTO = productService.deleteProduct(productId);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
}
