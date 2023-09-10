/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.service;

import java.util.List;
import java.util.Map;
import ou.cnh.pojo.Bus;

/**
 *
 * @author zedmo
 */
public interface BusService {
    List<Bus> getBuses(Map<String, String> params);
    int countBus();
    boolean addOrUpdateBus(Bus b);
    Bus getBusById(int id);
    boolean deleteBus(int id);
    boolean isExistBus(Bus b);
}
