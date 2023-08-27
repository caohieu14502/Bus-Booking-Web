/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.service;

import java.util.List;
import java.util.Map;
import ou.cnh.pojo.BusType;

/**
 *
 * @author zedmo
 */
public interface BusTypeService {
    List<BusType> getBusTypes(Map<String, String> params);
    boolean addOrUpdateBusType(BusType bt);
    BusType getBusTypeById(int id);
    boolean deleteBusType(int id);
}
