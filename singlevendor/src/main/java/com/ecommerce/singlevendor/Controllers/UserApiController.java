package com.ecommerce.singlevendor.Controllers;
import com.ecommerce.singlevendor.Entity.User;
import com.ecommerce.singlevendor.Implementation.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserApiController {
    @Autowired
    private UserImpl userImpl;

    @PostMapping("/createUser")
    public ResponseEntity<User> create_user(@RequestBody User user) {
        System.out.println("Request received - username: " + user.getUsername() + "password:" + user.getPassword() + "contact:" + user.getContact());
        return ResponseEntity.ok(userImpl.createUser(user));
    }

    @GetMapping("/viewAllUser")
    public List<User> viewAllUser(){
        System.out.println("Request received to view all users");
        return userImpl.viewAllUser();
    }


    // Edit an existing user
    @PutMapping("/edit/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User updatedUser) {
        System.out.println("Request received to edit a user details with ID: " + id);
        User user = userImpl.editUser(id, updatedUser.getUsername(), updatedUser.getPassword(),updatedUser.getContact());
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // Delete a user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        System.out.println("Request received to delete a user with ID: " + id);
        boolean isDeleted = userImpl.deleteUser(id);
        return isDeleted
                ? ResponseEntity.ok("User deleted successfully.")
                : ResponseEntity.status(404).body("User not found.");
    }
}


