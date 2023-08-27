/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.cnh.pojo.Seat;
import ou.cnh.repository.SeatRepository;
import ou.cnh.service.SeatService;

/**
 *
 * @author zedmo
 */
@Service
public class SeatServiceImpl implements SeatService{

    @Autowired
    private SeatRepository seatRepository;
    
    @Override
    public List<Seat> getSeats(Map<String, String> params) {
        return this.seatRepository.getSeats(params);
    }
    
}
