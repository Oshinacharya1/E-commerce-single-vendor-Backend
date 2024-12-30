package com.ecommerce.singlevendor.Implementation;

import com.ecommerce.singlevendor.Entity.Category;
import com.ecommerce.singlevendor.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryImpl {
    @Autowired
    private CategoryRepository categoryRepository;
    // Create a new category
    public Category createCategory(Category category) {
        System.out.println("Saving category to the database: " + category.getName());
        return categoryRepository.save(category);
    }

    // View all categories
    public List<Category> viewAllCategories() {
        System.out.println("Fetching all categories from the database");
        return categoryRepository.findAll();
    }

    // Get all products by category ID
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            System.out.println("Fetching products by category ID: " + id);
            return category.get();
        }
        return null; // Return null if the category does not exist
    }

    // Edit an existing category
    public Category editCategory(Long id, String name) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category category = existingCategory.get();
            category.setName(name);

            return categoryRepository.save(category); // Save the updated category
        }
        return null; // Return null if the category does not exist
    }

    // Delete a category by ID
    public boolean deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            System.out.println("Deleted category with ID: " + id);
            return true;
        }
        System.out.println("Category with ID: " + id + " not found");
        return false; // Return false if the category does not exist
    }
}
