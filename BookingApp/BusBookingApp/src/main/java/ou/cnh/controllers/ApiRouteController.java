/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ou.cnh.pojo.Route;
import ou.cnh.service.RouteService;

/**
 *
 * @author zedmo
 */
@RestController
@RequestMapping("/api")
public class ApiRouteController {
    
    @Autowired
    private RouteService routeService;
    @Autowired
    private Environment env;
    
    @GetMapping("/routes")
    @CrossOrigin
    public ResponseEntity<List<Route>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.routeService.getRoutes(params), HttpStatus.OK);
    }
    
    @GetMapping("/routes/pageSize")
    @CrossOrigin
    public ResponseEntity<Double> pageSize() {
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.routeService.countRoute();
        
        return new ResponseEntity<>(Math.ceil(count*1.0/pageSize), HttpStatus.OK);
    }
    
    @DeleteMapping("/handleRoute/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoute(@PathVariable(value = "id") int id) {
        this.routeService.deleteRoute(id);
    }
}
