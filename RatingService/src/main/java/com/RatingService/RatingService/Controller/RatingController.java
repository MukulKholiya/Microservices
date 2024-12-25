package com.RatingService.RatingService.Controller;

import com.RatingService.RatingService.Entity.Rating;
import com.RatingService.RatingService.Service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private Logger logger = LoggerFactory.getLogger(RatingController.class);
    @Autowired
    private RatingService ratingService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "HealthCheck";
    }
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings(){
//        logger.info(rating);
        List<Rating> ratings = ratingService.getAllRating();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody  Rating rating){

        Rating rating1 = ratingService.saveRating(rating);
        return new ResponseEntity<>(rating1,HttpStatus.OK);
    }
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId){
        return new ResponseEntity<>(ratingService.getRatingByHotelId(hotelId),HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId){
        return new ResponseEntity<>(ratingService.getRatingByUserId(userId),HttpStatus.OK);
    }

}
