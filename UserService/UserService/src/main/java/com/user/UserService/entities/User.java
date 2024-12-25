package com.user.UserService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
public class User {
    @Id
    private String userId;
    private String userName;
//    private String password;
    private String email;
    @Transient
    private List<Rating> ratings = new ArrayList<>();

}
