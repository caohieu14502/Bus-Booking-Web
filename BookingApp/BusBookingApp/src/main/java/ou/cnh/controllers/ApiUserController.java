/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.controllers;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ou.cnh.components.JwtService;
import ou.cnh.pojo.Ticket;
import ou.cnh.pojo.User;
import ou.cnh.service.TicketService;
import ou.cnh.service.UserService;

/**
 *
 * @author zedmo
 */
@RestController
@RequestMapping("/api")
public class ApiUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private TicketService ticketService;
    
    @DeleteMapping("/handleUser/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoute(@PathVariable(value = "id") int id) {
        this.userService.deleteUser(id);
    }
    
    @PutMapping("/handleUser/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void lockOrUnlock(@PathVariable(value = "id") int id) {
        this.userService.lockOrUnlock(id);
    }

    @PostMapping("/login/")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody User user) {
            System.out.printf("^^^^^^\n%s\n%s\n^^^^^^\n", user.getEmail(), user.getPassword());
        if (this.userService.authUser(user.getEmail(), user.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(user.getEmail());
            
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/test/")
    @CrossOrigin(origins = {"127.0.0.1:5500"})
    public ResponseEntity<String> test(Principal pricipal) {
        return new ResponseEntity<>("SUCCESSFUL", HttpStatus.OK);
    }
    
    @PostMapping(path = "/users/", 
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, 
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<User> addUser(@RequestParam Map<String, String> params, @RequestPart MultipartFile avatar) {
        User user = this.userService.addUser(params, avatar);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    
    @GetMapping(path = "/current-user/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<User> details(Principal user) {
        User u = this.userService.getUserByMail(user.getName());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
    
    @PostMapping(path="/printTicket/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Ticket>> printTicket(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.ticketService.getTickets(params), HttpStatus.OK);
    }
    
    @PostMapping(path="/userMail/")
    @CrossOrigin
    public ResponseEntity<User> user(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.userService.getUserByMail(params.get("email")), HttpStatus.OK);
    }
}
