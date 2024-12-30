package com.ecommerce.singlevendor.Controllers;

import com.ecommerce.singlevendor.Entity.Category;
import com.ecommerce.singlevendor.Entity.Product;
import com.ecommerce.singlevendor.Implementation.CategoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesApiController {
    @Autowired
    private CategoryImpl categoryImpl;

    // Create a new category
    @PostMapping("/createCategory")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        System.out.println("Request received to create a category: " + category.getName());
        return ResponseEntity.ok(categoryImpl.createCategory(category));
    }

    // View all categories
    @GetMapping("/viewAllCategories")
    public List<Category> viewAllCategories() {
        System.out.println("Request received to view all categories");
        return categoryImpl.viewAllCategories();
    }

    // View products in a category by category ID
    @GetMapping("/{id}/products")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Long id) {
        System.out.println("Request received to view products in category ID by category ID: " + id);
        Category category = categoryImpl.getCategoryById(id);
        return category != null
                 ? ResponseEntity.ok((List<Product>) category) : ResponseEntity.status(404).body(null); // Return 404 if the category is not found
    }


    // Update a category
    @PutMapping("/edit/{id}")
    public ResponseEntity<Category> editCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        System.out.println("Request received to edit category with ID: " + id);
        Category category = categoryImpl.editCategory(id, updatedCategory.getName());
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    // Delete a category by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        System.out.println("Request received to delete category with ID: " + id);
        boolean isDeleted = categoryImpl.deleteCategory(id);
        return isDeleted
                ? ResponseEntity.ok("Category deleted successfully.")
                : ResponseEntity.status(404).body("Category not found.");
    }

}
