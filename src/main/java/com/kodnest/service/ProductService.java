package com.kodnest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.entity.Category;
import com.kodnest.entity.Product;
import com.kodnest.entity.ProductImage;
import com.kodnest.repository.CategoryRepository;
import com.kodnest.repository.ProductImageRepository;
import com.kodnest.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getProductsByCategory(String categoryName) {

        if (categoryName != null && !categoryName.isEmpty()) {

            Optional<Category> categoryOpt = categoryRepository.findByCategoryName(categoryName);

            if (categoryOpt.isPresent()) {
                Category category = categoryOpt.get();
                return productRepository.findByCategory_CategoryId(category.getCategoryId());
            } else {
                throw new RuntimeException("Category not found");
            }

        } else {
            return productRepository.findAll();
        }
    }

    public List<String> getProductImages(Integer productId) {

        List<ProductImage> productImages =
                productImageRepository.findByProduct_ProductId(productId);

        List<String> imageUrls = new ArrayList<>();

        for (ProductImage image : productImages) {
            imageUrls.add(image.getImageUrl());
        }

        return imageUrls;
    }
}