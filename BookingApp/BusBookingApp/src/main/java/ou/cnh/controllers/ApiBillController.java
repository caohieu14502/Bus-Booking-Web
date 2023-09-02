/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ou.cnh.pojo.Cart;
import ou.cnh.service.BillService;

/**
 *
 * @author zedmo
 */
@RestController
@RequestMapping("/api")
public class ApiBillController {
    
    @Autowired
    private BillService billService;
    
    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    public void addBill(@RequestBody Map<String, Cart> carts) {
        this.billService.addBill(carts);
    }
}
