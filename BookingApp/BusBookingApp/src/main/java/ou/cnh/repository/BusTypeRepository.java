/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ou.cnh.repository;

import java.util.List;
import java.util.Map;
import ou.cnh.pojo.BusType;

/**
 *
 * @author zedmo
 */
public interface BusTypeRepository {
    List<BusType> getBusTypes(Map<String, String> params);
    boolean addOrUpdateBusType(BusType bt);
    BusType getBusTypeById(int id);
    boolean deleteBusType(int id);
}
