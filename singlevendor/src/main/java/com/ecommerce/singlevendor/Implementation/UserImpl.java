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

    public User editUser(Long id, String firstName, String lastName, String userName, String password, String contact) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setFirstName(firstName);
            user.setLastName(lastName);
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
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }

}
