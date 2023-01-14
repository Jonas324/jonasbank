package com.jonas.jonasbank.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<User> addNewUser(User user) {
        Optional<User> userOptional = userRepository.findByName(user.getUsername());
        if (userOptional.isPresent()){
            throw new IllegalStateException("username taken");
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setCredit(user.getCredit());
        newUser.setEnabled(user.isEnabled());
        newUser.setAccountNonExpired(user.isAccountNonExpired());
        newUser.setCredentialsNonExpired(user.isCredentialsNonExpired());
        newUser.setAccountNonLocked(user.isAccountNonLocked());

        userRepository.save(newUser);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> getUser(String username) {
        try {
            List<User> userList = new ArrayList<>(userRepository.findAll());

            return new ResponseEntity<>(userList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void deleteUser(Long Id) {
        boolean exists = userRepository.existsById(Id);
        if (!exists){
            throw new IllegalStateException("user with id" + Id + "does not exists");
        }
        userRepository.deleteById(Id);
    }
}
