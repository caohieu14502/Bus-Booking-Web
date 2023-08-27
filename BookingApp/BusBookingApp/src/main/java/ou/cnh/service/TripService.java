/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ou.cnh.service;

import java.util.List;
import java.util.Map;
import ou.cnh.pojo.Trip;

/**
 *
 * @author zedmo
 */
public interface TripService {
    List<Trip> getTrips(Map<String, String> params);
    int countTrip();
    Trip getTripById(int id);
    boolean addOrUpdateTrip(Trip t);
    boolean deleteTrip(int id);
    Integer setHolidayCost(Map<String, String> params);
}
