package com.RatingService.RatingService.Service;

import com.RatingService.RatingService.Entity.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    Rating saveRating(Rating rating);
    List<Rating> getAllRating();
    List<Rating> getRatingByUserId(String userId);
    List<Rating> getRatingByHotelId(String hotelId);
}
