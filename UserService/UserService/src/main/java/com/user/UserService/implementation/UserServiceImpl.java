package com.user.UserService.implementation;

import com.user.UserService.entities.Hotel;
import com.user.UserService.entities.Rating;
import com.user.UserService.entities.User;
import com.user.UserService.external.services.HotelService;
import com.user.UserService.external.services.RatingService;
import com.user.UserService.repositories.UserRepo;
import com.user.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepo userRepo;
    public UserServiceImpl(UserRepo userRepo){ // constructor injection
        this.userRepo = userRepo;
    }
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RatingService ratingService;

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userRepo.findAll();
        users.stream().forEach(user -> {
//            Rating[] forObject = restTemplate.getForObject("http://RATINGSERVICE/ratings/user/" + user.getUserId(), Rating[].class);
            List<Rating> dummyList = ratingService.getRatings(user.getUserId());
            List<Rating> list = dummyList.stream().map(rating -> {
//                ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
                Hotel feignHotel = hotelService.getHotel(rating.getHotelId());
//                rating.setHotel(forEntity.getBody());
                rating .setHotel(feignHotel);
                return rating;
            }).toList();
            user.setRatings(list);
        });

        return users;
    }

    @Override
    public User getUser(String Id) {

        User user = userRepo.findByUserId(Id);
//        fetch rating of above user
//        http://localhost:8082/ratings.user/{userId} ---------- api to be called

//        get for object was not sure when we were returning Arraylist, so we instead returned rating array.
//        Rating[] Arratings = restTemplate.getForObject("http://RATINGSERVICE/ratings/user/"+user.getUserId(), Rating[].class);
//        logger.info(String.valueOf(Arratings));
        List<Rating> ratings = ratingService.getRatings(user.getUserId());

        List<Rating> collect = ratings.stream().map(rating -> {
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel feignHotel = hotelService.getHotel(rating.getHotelId());
//            Hotel hotel = forEntity.getBody();
            rating.setHotel(feignHotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(collect);

        return user;
    }
}
