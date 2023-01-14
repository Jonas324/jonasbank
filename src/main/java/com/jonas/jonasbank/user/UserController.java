package com.jonas.jonasbank.user;

import com.jonas.jonasbank.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUser(@RequestParam(required = false) final String username){
        return userService.getUser(username);
    }

    @PostMapping
    public ResponseEntity<User> addNewUser(@RequestBody User user){
        // Postman -- { "email" : "hej@hejsan.se", "password" : "123", "username" : "benny", "credit" : "1000", "enabled" : "true", "accountNonExpired" : "true", "accountNonLocked" : "true", "credentialsNonExpired" : "true","role":"ADMIN"}
        userService.addNewUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);

        return "user with id " + id + " deleted";
    }

}
