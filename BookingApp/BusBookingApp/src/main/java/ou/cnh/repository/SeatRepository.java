/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ou.cnh.repository;

import java.util.List;
import java.util.Map;
import ou.cnh.pojo.Seat;

/**
 *
 * @author zedmo
 */
public interface SeatRepository {
    List<Seat> getSeats(Map<String, String> params);
    Seat getSeatById(int id);
    boolean addOrUpdateSeat(Seat s);
}
