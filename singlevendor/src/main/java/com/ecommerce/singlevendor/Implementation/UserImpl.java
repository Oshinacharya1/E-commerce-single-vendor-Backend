package com.ecommerce.singlevendor.Implementation;

import com.ecommerce.singlevendor.Entity.User;
import com.ecommerce.singlevendor.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserImpl {

    @Autowired
    private UserRepository userRepository;
    public User createUser (User user) {
        System.out.println("saving to database");
        return userRepository.save(user);
    }

    public List<User> viewAllUser() {
        System.out.println("saving to database");
        return userRepository.findAll();
    }

    public User editUser(Long id, String userName, String password, String contact) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(userName);
            user.setPassword(password);
            user.setContact(contact);

            return userRepository.save(user); // Save the updated user
        }
        return null; // Return null if the user does not exist
    }

    // Delete a user by ID
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false; // Return false if the user does not exist
    }

}
