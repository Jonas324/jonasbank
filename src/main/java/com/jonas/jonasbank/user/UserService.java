package com.jonas.jonasbank.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {


    ResponseEntity<User> addNewUser(User user);

    Optional<User> getUser(Long id);

    ResponseEntity<List<User>> getAllUsers(String username);

    void deleteUser(Long id);
}
