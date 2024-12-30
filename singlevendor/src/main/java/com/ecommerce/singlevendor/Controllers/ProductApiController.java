package com.ecommerce.singlevendor.Controllers;

import com.ecommerce.singlevendor.Entity.Product;
import com.ecommerce.singlevendor.Implementation.ProductImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductApiController {
    @Autowired
    private ProductImpl productImpl;

    @PostMapping("/createProduct")
    public ResponseEntity<Product> create_product(@RequestBody Product product) {
        System.out.println("Request received to create a product - price: " + product.getPrice() + "Image:" + product.getImage() + "status:" + product.getStatus() + "quantity:" + product.getQuantity());
        return ResponseEntity.ok(productImpl.createProduct(product));
    }

    @GetMapping("/viewAllProduct")
    public List<Product> viewAllProduct(){
        System.out.println("Request received to view all products");
        return productImpl.viewAllProduct();
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        System.out.println("Request received to fetch product with ID: " + id);
        Product product = productImpl.getProductById(id);
        return product != null
                ? ResponseEntity.ok(product)
                : ResponseEntity.status(404).body(null); // Return 404 if the product is not found
    }


    // Edit an existing product
    @RequestMapping ("/edit/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        System.out.println("Request received to edit a products details with ID: " + id);
        Product product = productImpl.editProduct(id, updatedProduct.getPrice(), updatedProduct.getImage(),updatedProduct.getStatus(), updatedProduct.getQuantity());
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    // Delete a product by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWithResponse(@PathVariable Long id) {
        System.out.println("Request received to delete a product by ID: " + id);
        boolean isDeleted = productImpl.deleteProduct(id);
        return isDeleted
                ? ResponseEntity.ok("Product deleted successfully.")
                : ResponseEntity.status(404).body("Product not found.");
    }
}
