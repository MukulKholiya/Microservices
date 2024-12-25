package com.hotel.HotelService.services;

import com.hotel.HotelService.entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {
    Hotel createHotel(Hotel hotel);
    List<Hotel> getAllHotel();
    Hotel getHotelById(String hotelId);
}
