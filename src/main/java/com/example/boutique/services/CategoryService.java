package com.example.boutique.services;

import com.example.boutique.entities.Category;
import com.example.boutique.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
    public Category getCategoryByName(String categoryName) {
       return categoryRepository.getCategoriesByName(categoryName);

    }

    public Category getCategoryById(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        return optionalCategory.get();
    }
    public Category updateCategory(Long id,Category category) {
        Category existingCategory= categoryRepository.findById(category.getId()).get();
        existingCategory.setName(category.getName());
        existingCategory.setProducts(category.getProducts());
        Category updatedCategory = categoryRepository.save(existingCategory);
        return updatedCategory;
    }


    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
    public void deleteAllCategories() {
        categoryRepository.deleteAll();
    }

}
