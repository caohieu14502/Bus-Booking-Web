/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.cnh.pojo.Route;
import ou.cnh.repository.RouteRepository;
import ou.cnh.service.RouteService;

/**
 *
 * @author zedmo
 */
@Service
public class RouteServiceImpl implements RouteService{
    @Autowired
    private RouteRepository routeRepo;

    @Override
    public List<Route> getRoutes(Map<String, String> params) {
        return this.routeRepo.getRoutes(params);
    }

    @Override
    public int countRoute() {
        return this.routeRepo.countRoute();
    }

    @Override
    public boolean addOrUpdateRoute(Route r) {

            return this.routeRepo.addOrUpdateRoute(r);
        
    }

    @Override
    public Route getRouteById(int id) {
        return this.routeRepo.getRouteById(id);
    }

    @Override
    public boolean deleteRoute(int id) {
        return this.routeRepo.deleteRoute(id);
    }

    @Override
    public boolean isExistRoute(Route r) {
        Map<String, String> params = new HashMap<>();
        params.put("origin", r.getOrigin().getProvince());
        params.put("destination", r.getDestination().getProvince());
        List<Route> routesCheck = this.routeRepo.getRoutes(params);
        if(routesCheck != null) 
            if(r.getId() != null && r.equals(routesCheck.get(0)))
                return false;
        return true;
    }
}
