package com.user.UserService.implementation;

import com.user.UserService.entities.User;
import com.user.UserService.repositories.UserRepo;
import com.user.UserService.services.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
@Component
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    public UserServiceImpl(UserRepo userRepo){ // constructor injection
        this.userRepo = userRepo;
    }
    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(String Id) {
        return userRepo.findByUserId(Id);
    }
}
