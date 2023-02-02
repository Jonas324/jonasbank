package com.jonas.jonasbank.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT s FROM User s WHERE s.username = ?1")
    Optional<User> findByName(String username);

    @Query("SELECT s FROM User s WHERE s.userId = ?1")
    Optional<User> findById(Long id);

    @Query("SELECT s FROM User s WHERE s.username = ?1")
    UserDetails findByUsername(String username);
}
