/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ou.cnh.pojo.Feedback;
import ou.cnh.pojo.Trip;
import ou.cnh.service.FeedbackService;
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
    @Autowired
    private FeedbackService feedbackService;
    @Resource(name = "simpleDateFormat")
    private SimpleDateFormat simpleDateFormat;
    @Autowired
    private Environment env;
    
    @GetMapping("/trips")
    @CrossOrigin
    public ResponseEntity<List<Trip>> list(@RequestParam Map<String, String> params) {
        if(params.get("setOff") == null)
            params.put("setOff", simpleDateFormat.format(new Date()));
        return new ResponseEntity<>(this.tripService.getTrips(params), HttpStatus.OK);
    }
    
    @GetMapping("/trips/pageSize")
    @CrossOrigin
    public ResponseEntity<Double> pageSize() {
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.tripService.countTrip();
        
        return new ResponseEntity<>(Math.ceil(count*1.0/pageSize), HttpStatus.OK);
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
    
    @GetMapping(path = "/trips/{tripId}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Feedback>> listFeedback(@PathVariable(value = "tripId") int id ) {
        Map<String, String> params = new HashMap<>();
        params.put("tripId", String.format("%d", id));
        return new ResponseEntity<>(this.feedbackService.getFeedbacks(id), HttpStatus.OK);
    }
    
    @PostMapping(path = "/comments/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback comment) {
        Feedback c = this.feedbackService.addOrUpdateFeedback(comment);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }
    
}
