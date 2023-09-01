/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ou.cnh.pojo.Trip;
import ou.cnh.service.TripService;

/**
 *
 * @author zedmo
 */
@RestController
@RequestMapping("/api")
public class ApiTripController {
    @Autowired
    private TripService tripService;
    
    @GetMapping("/trips")
    @CrossOrigin
    public ResponseEntity<List<Trip>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.tripService.getTrips(params), HttpStatus.OK);
    }
    
    @DeleteMapping("/handleTrip/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoute(@PathVariable(value = "id") int id) {
        this.tripService.deleteTrip(id);
    }
    
    @GetMapping(path = "/trips/{tripId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Trip> detail(@PathVariable(value = "tripId") int id ) {
        return new ResponseEntity<>(this.tripService.getTripById(id), HttpStatus.OK);
    }
}
