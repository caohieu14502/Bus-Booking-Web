/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.repository;

import java.util.List;
import java.util.Map;
import ou.cnh.pojo.Station;

/**
 *
 * @author zedmo
 */
public interface StationRepository {
    List<Station> getStations(Map<String, String> params);
    int countStation();
    boolean addOrUpdateStation(Station s);
    Station getStationById(int id);
    boolean deleteStation(int id);
}
