package com.jonas.jonasbank.user;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserService {


    private final  UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Optional<User> getUser(Integer id) {
        return userRepository.findById(Long.valueOf(id));
    }
}
