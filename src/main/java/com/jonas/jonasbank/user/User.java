package com.jonas.jonasbank.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "money")
    private Long money;

    public User(){}

    public User(String name, String lastName, Long money) {
        this.name = name;
        this.lastName = lastName;
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getMoney() {
        return money;
    }

    public void setName(Long money){
        this.money = money;
    }
}
