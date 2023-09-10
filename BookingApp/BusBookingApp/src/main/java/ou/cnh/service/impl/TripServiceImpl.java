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
        if (t.getId() == null) { // add
            if (this.tripRepo.addOrUpdateTrip(t)) {
                Map<String, String> params = new HashMap<>();
                params.put("busId", t.getBusId().getId().toString());
                this.seatRepo.getSeats(params).forEach(s -> {
                    Ticket tick = new Ticket();
                    tick.setSeatId(s);
                    double x = this.routeRepo.getRouteById(t.getRouteId().getId()).getBasicPrice() * (t.getHolidayCost() + this.busRepo.getBusById(t.getBusId().getId()).getBusTypeId().getTypeCost() - 1);
                    double roundX = Math.ceil(x / 1000) * 1000;
                    System.out.printf("^^^\n%.1f\n%.1f\n%.1f\n%.1f\n^^^^\n", this.routeRepo.getRouteById(t.getRouteId().getId()).getBasicPrice(), t.getHolidayCost(), this.busRepo.getBusById(t.getBusId().getId()).getBusTypeId().getTypeCost() - 1, x);
                    tick.setPrice(roundX);
                    tick.setIsAvailable(true);
                    tick.setTripId(t);
                    this.ticketRepo.addOrUpdateTicket(tick);
                });
                return true;
            }
            return false;
        } else { //update
            return this.tripRepo.addOrUpdateTrip(t);
        }
    }

    @Override
    public boolean deleteTrip(int id) {
        return this.tripRepo.deleteTrip(id);
    }

    @Override
    public Integer setHolidayCost(Map<String, String> params) {
        return this.tripRepo.setHolidayCost(params);
    }

    @Override
    public boolean isExits(Trip t) {
        Map<String, String> params = new HashMap<>();
        params.put("setOff", t.getSetOffDayString());
        params.put("setOffTime", t.getSetOffTimeString());
        List<Trip> tripsCheck = this.tripRepo.getTrips(params);
        if(tripsCheck != null) {
            System.out.printf("^^\n%s\n%s\n^^",t.getDriverId(), tripsCheck.get(0).getDriverId());
            if(t.getId() != null && (!t.getDriverId().equals(tripsCheck.get(0).getDriverId()) || !t.getBusId().equals(tripsCheck.get(0).getBusId())))
                return false;
            
        }
        return true;
    }

}
