package com.jonas.jonasbank.user;

import com.jonas.jonasbank.config.AppPasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;
    private final AppPasswordConfig appPasswordConfig;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, AppPasswordConfig appPasswordConfig, UserRepository userRepository){
        this.userService = userService;
        this.appPasswordConfig = appPasswordConfig;
        this.userRepository = userRepository;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable("id") Long id){
        return userService.getUser(id);
    }

    @CrossOrigin
    @GetMapping("/adminPage")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminPage(){
        return "welcome to adminPage";
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(String username){
        return userService.getAllUsers(username);
    }

    @CrossOrigin
    @GetMapping("/register")
    public String displayRegisterUser(User user) {    // THIS ARGUMENT MUST EXIST

        return "register";
    }

    @CrossOrigin
    @PostMapping("/register")
    public String registerUser(@RequestBody User userModel, BindingResult result, Model model) {

        if (result.hasErrors()) {

            return "register";
        }

        userModel.setUsername(userModel.getUsername());
        userModel.setPassword(appPasswordConfig.bCryptPasswordEncoder().encode(userModel.getPassword()));
        userModel.setCredit(1500);
        userModel.setRole(Role.ROLE_ADMIN);
        userModel.setAccountNonLocked(true);
        userModel.setAccountNonExpired(true);
        userModel.setEnabled(true);
        userModel.setCredentialsNonExpired(true);

        // IF no errors
        System.out.println(userModel);
        userRepository.save(userModel);
        // model.addAttribute("user", userModel);

        return "user registered";
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<User> addNewUser(@RequestBody User user, BindingResult result, Model model){
        // Postman -- {  "password" : "123", "username" : "benny", "credit" : "1000", "enabled" : "true", "accountNonExpired" : "true", "accountNonLocked" : "true", "credentialsNonExpired" : "true","role":"ADMIN"}
        userService.addNewUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);

        return "user with id " + id + " deleted";
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public String updateCredit(@PathVariable("id") User user){
        return "new credit claculated " + user.getCredit();
    }

}
