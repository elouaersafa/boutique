package com.example.boutique.Controller;


import com.example.boutique.entities.Category;
import com.example.boutique.entities.Product;
import com.example.boutique.services.CategoryService;
import com.example.boutique.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductControllers {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = productService.getAllProducts();

        List<Category> categories = this.categoryService.getAllCategories();
        model.addAttribute("products", products);
        //model.addAttribute("title", "StoreWala | Start Shopping Now!");
        model.addAttribute("categories", categories);

        return "home";
    }

    @GetMapping("/products")
    public String viewProductsList(Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        return "productList";
    }
    @PostMapping("/products/news")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.createProduct(product);
        return  "redirect:/products";
    }
    @GetMapping("/products/new")
    public String addNewProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "newProduct";
    }
    @GetMapping("products/showFormForUpdate/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/";
        }
        model.addAttribute("product", product);
        return "updatedProduct";
    }

    @PostMapping("products/updated")
    public String editProduct(Product product) {
        productService.createProduct(product);
        return "redirect:/products"; // Redirige vers la page d'accueil ou une autre page après la modification
    }
  /*  @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "updatedProduct";
    }*/

    @GetMapping("/products/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        productService.deleteProduct(id);
        return "redirect:/products";

    }

    @GetMapping("/products/search")
    public String searchProductsByCategory(@RequestParam("categoryName") String categoryName, Model model) {
        // Récupérer la catégorie par son nom
        Category category = categoryService.getCategoryByName(categoryName);

        if (category != null) {
            // Si la catégorie existe, récupérer les produits associés
            List<Product> products = productService.getProductByCategory(category);
            model.addAttribute("products", products);
        }

        return "searchProduct";
    }


        }
