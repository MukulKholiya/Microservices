package com.RatingService.RatingService.Repository;

import com.RatingService.RatingService.Entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepo extends MongoRepository<Rating,String> {

    List<Rating> findRatingsByUserId(String userId);
    List<Rating> findRatingsByHotelId(String hotelId);
}
