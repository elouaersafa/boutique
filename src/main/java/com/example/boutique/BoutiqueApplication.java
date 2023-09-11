package com.example.boutique;

import com.example.boutique.entities.Category;
import com.example.boutique.entities.Product;
import com.example.boutique.repositories.CategoryRepository;
import com.example.boutique.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BoutiqueApplication implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(BoutiqueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product product1 = new Product();
		product1.setName("pull");
		product1.setDescription("pour femme ");
		product1.setPrice(200D);
		List<Product> products=productRepository.findAll();
		productRepository.save(product1);


		Category category1 = new Category();
		category1.setName("pull");
		//category1.setProducts(products);
		categoryRepository.save(category1);






	}

}
