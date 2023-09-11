package com.example.boutique.repositories;

import com.example.boutique.entities.Category;
import com.example.boutique.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product, Long> {
List<Product> getProductsByCategory_Name(String categoryName);
    List<Product> getProductsByCategory(Category category);
}
