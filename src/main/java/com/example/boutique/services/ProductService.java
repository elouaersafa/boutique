package com.example.boutique.services;

import com.example.boutique.entities.Product;
import com.example.boutique.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }


    public Product getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.get();
    }
    public Product updateProduct(Long id,Product product) {
        Product existingProduct= productRepository.findById(product.getId()).get();
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());

        Product updatedProduct = productRepository.save(existingProduct);
        return updatedProduct;
    }


    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

}
