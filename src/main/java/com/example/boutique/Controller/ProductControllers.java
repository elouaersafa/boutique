package com.example.boutique.Controller;


import com.example.boutique.entities.Product;
import com.example.boutique.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductControllers {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
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
        return "newProduct";
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
}
