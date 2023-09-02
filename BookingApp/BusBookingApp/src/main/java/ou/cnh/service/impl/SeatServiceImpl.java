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
import ou.cnh.repository.BusRepository;
import ou.cnh.repository.SeatRepository;
import ou.cnh.service.SeatService;

/**
 *
 * @author zedmo
 */
@Service
public class SeatServiceImpl implements SeatService{
    @Autowired
    private SeatRepository seatRepo;
    @Autowired
    private BusRepository busRepo;

    @Override
    public List<Seat> getSeats(Map<String, String> params) {
        return this.seatRepo.getSeats(params);
    }

    @Override
    public Seat getSeatById(int id) {
        return this.seatRepo.getSeatById(id);
    }

    @Override
    public boolean addOrUpdateSeat(String[] seats, int busId) {
        if(seats == null)
            return false;
        try{
            for(String x : seats) {
                Seat s = new Seat();
                s.setSeatColPos(Integer.valueOf(String.valueOf(x.charAt(0))));
                s.setSeatRowPos(Integer.valueOf(String.valueOf(x.charAt(1))));
                s.setBusId(this.busRepo.getBusById(busId));
                if(!this.seatRepo.addOrUpdateSeat(s))
                    throw new RuntimeException("hi");
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
}
