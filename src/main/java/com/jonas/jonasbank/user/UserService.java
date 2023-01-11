package com.jonas.jonasbank.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository.findByName(user.getUsername());
        if (userOptional.isPresent()){
            throw new IllegalStateException("guestName taken");
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setCredit(1000);
        userRepository.save(newUser);
    }

    public Optional<User> getUser(String username) {

        return userRepository.findByName(username);
    }
}
