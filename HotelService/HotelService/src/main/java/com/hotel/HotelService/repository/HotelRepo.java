package com.hotel.HotelService.repository;

import com.hotel.HotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepo extends JpaRepository<Hotel,String> {
    Hotel getHotelByHotelId(String hotelId);
}
