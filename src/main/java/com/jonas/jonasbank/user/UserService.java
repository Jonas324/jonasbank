package com.jonas.jonasbank.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {


    ResponseEntity<User> addNewUser(User user);

    ResponseEntity<List<User>> getUser(String username);

    void deleteUser(Long id);
}
