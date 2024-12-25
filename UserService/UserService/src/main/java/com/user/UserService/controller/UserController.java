package com.user.UserService.controller;

import com.user.UserService.entities.User;
import com.user.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


//    create user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){

        return new ResponseEntity<>(userService.saveUser(user),null,200);
    }
//    get single user
    @GetMapping("/{Id}")
    public ResponseEntity<User> getUser(@PathVariable String Id){
        User user = userService.getUser(Id);

        return ResponseEntity.ok(user);
    }

//  get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUser(),null,200);
    }

}
