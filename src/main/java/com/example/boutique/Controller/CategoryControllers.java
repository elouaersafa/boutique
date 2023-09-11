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
@RequestMapping
@Controller
public class CategoryControllers {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("/categories")
    public String viewCategoriesList(Model model) {
        model.addAttribute("categoryList", categoryService.getAllCategories());
        Category category = new Category();
        model.addAttribute("saveCategory", category);
        return "categoryList";
    }
    @PostMapping("/addCategory")
    public String saveCategory(@ModelAttribute("saveCategory") Category category) {
        categoryService.createCategory(category);
        return  "redirect:/categories";
    }



   @GetMapping("/categories/showFormForUpdate/{id}")
    public String showEditCategoryForm(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return "redirect:/";
        }
        model.addAttribute("category", category);
        return "updatedCategory";
    }

    @PostMapping("/categories/updated")
    public String editCategory(Category category) {
        Category savedCategory = categoryService.createCategory(category);
        for (Product product : category.getProducts()) {
            product.setCategory(savedCategory);
            productService.createProduct(product);
        }
        return "redirect:/categories";
    }
   @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
       Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "updatedCategory";
    }

   @GetMapping("/categories/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
       categoryService.deleteCategory(id);
        return "redirect:/categories";

    }
}
