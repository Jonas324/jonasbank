package com.jonas.jonasbank.user;

import jakarta.persistence.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableWebSecurity
@Entity
@Table(name = "BankUser")
public class User implements UserDetails  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String email;
    private String password;
    private String username;
    private int credit;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private boolean credentialsNonExpired;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User(String email,
                int credit,
                String username,
                String password,
                Boolean enabled,
                Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.credit = credit;
        this.enabled=enabled;
        this.username=username;
        this.password=password;
        this.accountNonExpired=true;
        this.accountNonLocked=true;
        this.credentialsNonExpired=true;



    }

    public User(String email,
                int credit,
                String password,
                String username,
                Boolean enabled,
                Boolean accountNonExpired,
                Boolean accountNonLocked,
                boolean credentialsNonExpired,
                Collection<? extends GrantedAuthority> authorities) {

        this.email = email;
        this.credit = credit;
        this.password = password;
        this.username = username;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;


    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;
    }



    public User() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }



    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }


    public void eraseCredentials(){
        this.password=null;
    }

}
