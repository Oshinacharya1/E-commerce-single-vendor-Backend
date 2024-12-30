package com.ecommerce.singlevendor.Repositories;

import com.ecommerce.singlevendor.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);

}
