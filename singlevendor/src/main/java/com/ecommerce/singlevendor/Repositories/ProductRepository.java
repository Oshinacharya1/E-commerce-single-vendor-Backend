package com.ecommerce.singlevendor.Repositories;

import com.ecommerce.singlevendor.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}