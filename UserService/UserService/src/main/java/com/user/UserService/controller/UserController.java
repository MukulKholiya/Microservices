package com.user.UserService.controller;

import com.user.UserService.entities.User;
import com.user.UserService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


//    create user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){

        return new ResponseEntity<>(userService.saveUser(user),null,200);
    }
//    get single user
    @GetMapping("/{Id}")
    @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable String Id){
        User user = userService.getUser(Id);

        return ResponseEntity.ok(user);
    }
    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
        logger.info("Fallback is executed because service is down : ", ex.getMessage());
        User user = new User();
        user.setUserId(null);
        user.setEmail("dummy@gmail.com");
        user.setUserName("null");
        return ResponseEntity.ok(user);
    }

//  get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUser(),null,200);
    }

}
