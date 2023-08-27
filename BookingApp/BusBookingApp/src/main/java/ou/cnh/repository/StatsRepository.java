/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ou.cnh.repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author zedmo
 */
public interface StatsRepository {
    public List<Object[]> countTripByRoutes();
    public List<Object[]> statesRevenue(Map<String, String> params);
}
