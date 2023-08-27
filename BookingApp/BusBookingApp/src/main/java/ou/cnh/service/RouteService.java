/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ou.cnh.service;

import java.util.List;
import java.util.Map;
import ou.cnh.pojo.Route;

/**
 *
 * @author zedmo
 */
public interface RouteService {
    List<Route> getRoutes(Map<String, String> params);
    int countRoute();
    boolean addOrUpdateRoute(Route r);
    Route getRouteById(int id);
    boolean deleteRoute(int id);
}
