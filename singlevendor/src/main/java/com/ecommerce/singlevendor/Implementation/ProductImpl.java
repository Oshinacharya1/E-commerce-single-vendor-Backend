package com.ecommerce.singlevendor.Implementation;

import com.ecommerce.singlevendor.Entity.Product;
import com.ecommerce.singlevendor.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImpl {

    @Autowired
    private ProductRepository productRepository;

    // Create a new product
    public Product createProduct(Product product) {
        System.out.println("Saving product to the database: ");
        return productRepository.save(product);
    }

    // View all products
    public List<Product> viewAllProduct() {
        System.out.println("Fetching all products from the database");
        return productRepository.findAll();
    }

    // Get product by ID
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            System.out.println("Fetching product with ID: " + id);
            return product.get();
        }
        System.out.println("Product with ID: " + id + " not found");
        return null; // Return null if the product does not exist
    }

    // Edit an existing product
    public Product editProduct(Long id, Double price, String image, String status, Integer quantity) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setPrice(price);
            product.setImage(image);
            product.setStatus(status);
            product.setQuantity(quantity);

            return productRepository.save(product); // Save the updated product
        }
        System.out.println("Product with ID: " + id + " not found");
        return null; // Return null if the product does not exist
    }

    // Delete a product by ID
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            System.out.println("Deleted product with ID: " + id);
            return true;
        }
        System.out.println("Product with ID: " + id + " not found");
        return false; // Return false if the product does not exist
    }
}