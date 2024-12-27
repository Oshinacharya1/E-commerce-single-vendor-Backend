package com.ecommerce.singlevendor.Implementation;

import com.ecommerce.singlevendor.Entity.Cart;
import com.ecommerce.singlevendor.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartImpl {

    @Autowired
    private CartRepository cartRepository;

    public Cart createCart(Cart cart) {
        System.out.println("saving to database");
        return cartRepository.save(cart);
    }

    public List<Cart> viewAllCart() {
        System.out.println("saving to database");
        return cartRepository.findAll();
    }

    public Cart editCart(Long id, String productName, Integer quantity) {
        Optional<Cart> existingCart = cartRepository.findById(id);
        if (existingCart.isPresent()) {
            Cart cart = existingCart.get();
            cart.setProductName(productName);
            cart.setQuantity(Integer.valueOf(quantity));

            return cartRepository.save(cart); // Save the updated cart
        }
        return null; // Return null if the cart does not exist
    }

    // Delete a cart by ID
    public boolean deleteCart(Long id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
            return true;
        }
        return false; // Return false if the cart does not exist
    }

}
