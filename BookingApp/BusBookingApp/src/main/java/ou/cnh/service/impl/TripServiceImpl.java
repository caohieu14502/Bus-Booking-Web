/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.cnh.pojo.Trip;
import ou.cnh.repository.RouteRepository;
import ou.cnh.repository.TripRepository;
import ou.cnh.service.TripService;

/**
 *
 * @author zedmo
 */
@Service
public class TripServiceImpl implements TripService{
    @Autowired
    private TripRepository tripRepo;
    @Autowired
    private RouteRepository routeRepo;
    
    @Override
    public List<Trip> getTrips(Map<String, String> params) {
//        if(params != null){
//            String ori = params.get("original");
//            String des = params.get("destination");
//            if(ori != null && !ori.isEmpty() || des != null && !des.isEmpty()) {
//                Map<String, String> routeParams = new HashMap<>();
//                routeParams.put("origin", ori);
//                routeParams.put("destination", des);
//                routeRepo.getRoutes(routeParams)
//            }
//        }
        return this.tripRepo.getTrips(params);
    }

    @Override
    public int countTrip() {
        return this.tripRepo.countTrip();
    }

    @Override
    public Trip getTripById(int id) {
        return this.tripRepo.getTripById(id);
    }

    @Override
    public boolean addOrUpdateTrip(Trip t) {
        return this.tripRepo.addOrUpdateTrip(t);
    }
    
    @Override
    public boolean deleteTrip(int id) {
        return this.tripRepo.deleteTrip(id);
    }

    @Override
    public Integer setHolidayCost(Map<String, String> params) {
        return this.tripRepo.setHolidayCost(params);
    }
    
}
