package com.ecommerce.singlevendor.Repositories;

import com.ecommerce.singlevendor.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}


