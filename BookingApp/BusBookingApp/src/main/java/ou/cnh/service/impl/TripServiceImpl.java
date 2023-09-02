/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.cnh.pojo.Ticket;
import ou.cnh.pojo.Trip;
import ou.cnh.repository.BusRepository;
import ou.cnh.repository.RouteRepository;
import ou.cnh.repository.SeatRepository;
import ou.cnh.repository.TicketRepository;
import ou.cnh.repository.TripRepository;
import ou.cnh.service.TripService;

/**
 *
 * @author zedmo
 */
@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepo;
    @Autowired
    private RouteRepository routeRepo;
    @Autowired
    private TicketRepository ticketRepo;
    @Autowired
    private SeatRepository seatRepo;
    @Autowired
    private BusRepository busRepo;

    @Override
    public List<Trip> getTrips(Map<String, String> params) {
//        if(params != null){
//            String ori = params.get("original");
//            String des = params.get("destination");
//            if(ori != null && !ori.isEmpty() || des != null && !des.isEmpty()) {
//                Map<String, String> routeParams = new HashMap<>();
//                routeParams.put("origin", ori);
//                routeParams.put("destination", des);
//                params.put("routeId", routeRepo.getRoutes(routeParams).get(0).getId().toString());
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
        if (this.tripRepo.addOrUpdateTrip(t)) {
//            if(t.getId() == null && t.getBusId() != null) {
            Map<String, String> params = new HashMap<>();
            params.put("busId", t.getBusId().getId().toString());
            this.seatRepo.getSeats(params).forEach(s -> {
                Ticket tick = new Ticket();
                tick.setSeatId(s);
                float x = t.getRouteId().getBasicPrice() * (t.getHolidayCost() + this.busRepo.getBusById(t.getBusId().getId()).getBusTypeId().getTypeCost() - 1);
                System.out.printf("^^^\n%.1f\n%.1f\n%.1f\n%.1f\n^^^^\n",t.getRouteId().getBasicPrice(), t.getHolidayCost(), this.busRepo.getBusById(t.getBusId().getId()).getBusTypeId().getTypeCost() - 1, x);
                tick.setPrice((double) x);
                tick.setIsAvailable(true);
                tick.setTripId(t);
                this.ticketRepo.addOrUpdateTicket(tick);
            });
//        }
            return true;
        }
        return false;
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
