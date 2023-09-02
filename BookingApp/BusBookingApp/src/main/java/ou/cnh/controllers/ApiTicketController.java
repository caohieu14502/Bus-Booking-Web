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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ou.cnh.pojo.Ticket;
import ou.cnh.service.TicketService;

/**
 *
 * @author zedmo
 */
@RestController
@RequestMapping("/api")
public class ApiTicketController {
    
    @Autowired
    private TicketService ticketService;
    
//    @GetMapping(path = "/tickets/{tripId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @CrossOrigin
//    public ResponseEntity<Trip> detail(@PathVariable(value = "tripId") int id ) {
//        return new ResponseEntity<>(this.tripService.getTripById(id), HttpStatus.OK);
//    }
    
    @GetMapping("/tickets")
    @CrossOrigin
    public ResponseEntity<List<Ticket>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.ticketService.getTickets(params), HttpStatus.OK);
    }
}
