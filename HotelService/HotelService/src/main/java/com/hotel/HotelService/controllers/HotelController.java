package com.hotel.HotelService.controllers;

import com.hotel.HotelService.entities.Hotel;
import com.hotel.HotelService.services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;
    public HotelController(HotelService hotelService){
        this.hotelService = hotelService;
    }
//    create
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
    }
//    get single
    @GetMapping("/{Id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable  String Id){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelById(Id));
    }
//    get all
    @GetMapping
    public ResponseEntity<List<Hotel>> findAllHotels(){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllHotel());
    }
}
