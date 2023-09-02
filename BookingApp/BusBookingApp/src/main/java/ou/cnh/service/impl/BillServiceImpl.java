/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.service.impl;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.cnh.pojo.Cart;
import ou.cnh.repository.BillRepository;
import ou.cnh.service.BillService;

/**
 *
 * @author zedmo
 */
@Service
public class BillServiceImpl implements BillService{
    @Autowired
    private BillRepository billRepo;

    @Override
    public boolean addBill(Map<String, Cart> carts) {
        return this.billRepo.addBill(carts);
    }
    
}
