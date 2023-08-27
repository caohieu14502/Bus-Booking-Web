/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ou.cnh.repository;

import java.util.List;
import java.util.Map;
import ou.cnh.pojo.Bus;

/**
 *
 * @author zedmo
 */
public interface BusRepository {
    List<Bus> getBuses(Map<String, String> params);
    int countBus();
    boolean addOrUpdateBus(Bus b);
    Bus getBusById(int id);
    boolean deleteBus(int id);
}
