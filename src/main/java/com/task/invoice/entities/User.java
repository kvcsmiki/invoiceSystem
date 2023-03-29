package com.task.invoice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String username;
    private String password;
    private Date loginDate;
    private List<Role> role;

    public User(String username, String password, List<Role> role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
