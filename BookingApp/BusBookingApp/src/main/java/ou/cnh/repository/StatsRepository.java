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
    List<Object[]> countTripByRoutes();
    List<Object[]> statesRevenue(Map<String, String> params);
    List<Object[]> countBookedTicketByRoutes();
    List<Object[]> statesRevenueTotal(Map<String, String> params);
}
