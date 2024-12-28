package com.ecommerce.singlevendor.Repositories;

import com.ecommerce.singlevendor.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
}
