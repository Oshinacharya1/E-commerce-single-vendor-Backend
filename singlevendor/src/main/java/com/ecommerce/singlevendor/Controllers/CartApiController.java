package com.ecommerce.singlevendor.Controllers;

import com.ecommerce.singlevendor.Entity.Cart;
import com.ecommerce.singlevendor.Implementation.CartImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartApiController {
    @Autowired
    private CartImpl cartImpl;

    @PostMapping("/createCart")
    public ResponseEntity<Cart> create_cart(@RequestBody Cart cart) {
        System.out.println("Request received - products: " + cart.getProductName() + "quantity:" + cart.getQuantity());
        return ResponseEntity.ok(cartImpl.createCart(cart));
    }

    @GetMapping("/viewAllCart")
    public List<Cart> viewAllCart(){
        System.out.println("Request received to view all carts");
        return cartImpl.viewAllCart();
    }


    // Edit an existing cart
    @PutMapping("/edit/{id}")
    public ResponseEntity<Cart> editCart(@PathVariable Long id, @RequestBody Cart updatedCart) {
        System.out.println("Request received to edit a cart with ID: " + id);
        Cart cart = cartImpl.editCart(id, updatedCart.getProductName(), updatedCart.getQuantity());
        return cart != null ? ResponseEntity.ok(cart) : ResponseEntity.notFound().build();
    }

    // Delete a cart by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long id) {
        System.out.println("Request received to delete a cart with ID: " + id);
        boolean isDeleted = cartImpl.deleteCart(id);
        return isDeleted
                ? ResponseEntity.ok("Product deleted successfully.")
                : ResponseEntity.status(404).body("Product not found.");
    }
}

