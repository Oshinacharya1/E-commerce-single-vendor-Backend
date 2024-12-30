package com.ecommerce.singlevendor.Repositories;

import com.ecommerce.singlevendor.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
