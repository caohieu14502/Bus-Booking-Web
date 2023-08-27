/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ou.cnh.service;

import java.util.List;
import java.util.Map;
import ou.cnh.pojo.Station;

/**
 *
 * @author zedmo
 */
public interface StationService {
    List<Station> getStations(Map<String, String> params);
    int countStation();
    boolean addOrUpdateStation(Station s);
    Station getStationById(int id);
    boolean deleteStation(int id);
}
