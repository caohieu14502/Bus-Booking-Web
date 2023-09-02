/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.pojo;

import lombok.Data;

/**
 *
 * @author zedmo
 */
@Data
public class Cart {
    private int id;
    private Double unitPrice;
    private String origin;
    private String destination;
    private String setOffTime;
    private String setOffDay;
    private String seatRowPos;
    private String seatColPos;
 }
