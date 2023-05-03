package com.jonas.jonasbank.user;

import com.jonas.jonasbank.config.AppPasswordConfig;
import com.jonas.jonasbank.transaction.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AppPasswordConfig appPasswordConfig;

    public UserServiceImpl(UserRepository userRepository, AppPasswordConfig appPasswordConfig) {
        this.userRepository = userRepository;
        this.appPasswordConfig = appPasswordConfig;
    }

    @Override
    public Optional<User> getUser(Long id){
        Optional<User> user = userRepository.findById(id);
        return user;
    }
    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> userList = new ArrayList<>();
            userList.addAll(userRepository.findAll());

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

    public void calculateCredit(Transaction transaction){


        Long userSendId = transaction.getSenderId();
        Long userReceiveId = transaction.getReceiverId();
        int credit = transaction.getCredit();

        Optional<User> _userSend =  userRepository.findById(userSendId);
        Optional<User> _userReceive = userRepository.findById(userReceiveId);
        User userS = _userSend.get();
        User userR = _userReceive.get();

        int userSCredit = userS.getCredit();
        int userRCredit = userR.getCredit();

        int userSUpdatedCredit = userSCredit - credit;
        int userRUpdatedCredit = userRCredit + credit;

        userS.setCredit(userSUpdatedCredit);
        userR.setCredit(userRUpdatedCredit);

        userRepository.save(userS);
        userRepository.save(userR);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);    // Query

    }
}
