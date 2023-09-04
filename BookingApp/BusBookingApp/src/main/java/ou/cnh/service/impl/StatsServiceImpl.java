/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.cnh.repository.StatsRepository;
import ou.cnh.service.StatsService;

/**
 *
 * @author zedmo
 */
@Service
public class StatsServiceImpl implements StatsService{
    @Autowired
    private StatsRepository statsRepo;

    @Override
    public List<Object[]> countTripByRoutes() {
        return this.statsRepo.countTripByRoutes();
    }

    @Override
    public List<Object[]> statesRevenue(Map<String, String> params) {
        return this.statsRepo.statesRevenue(params);
    }

    @Override
    public List<Object[]> statesRevenueTotal(Map<String, String> params) {
        return this.statsRepo.statesRevenueTotal(params);
    }

    @Override
    public List<Object[]> countBookedTicketByRoutes() {
        return this.statsRepo.countBookedTicketByRoutes();
    }
    
}
