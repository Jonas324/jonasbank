package com.jonas.jonasbank.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public Optional<User> getUser(String username){
        Optional<User> user = userService.getUser("benny");
        System.out.println(user);
        return user;
    }

    @PostMapping
    public ResponseEntity<User> registerNewUser(@RequestBody User user){
        userService.addNewUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
