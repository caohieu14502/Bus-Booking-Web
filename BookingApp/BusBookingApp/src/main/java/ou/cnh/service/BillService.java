/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ou.cnh.service;

import java.util.Map;
import ou.cnh.pojo.Cart;

/**
 *
 * @author zedmo
 */
public interface BillService {
    boolean addBill(Map<String, Cart> carts);
}
