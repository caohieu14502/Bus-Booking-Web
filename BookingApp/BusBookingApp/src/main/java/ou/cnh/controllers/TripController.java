/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.controllers;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ou.cnh.pojo.Trip;
import ou.cnh.service.BusService;
import ou.cnh.service.RouteService;
import ou.cnh.service.TripService;

/**
 *
 * @author zedmo
 */
@Controller
@RequestMapping("/admin")
public class TripController {
    @Autowired
    private RouteService routeService;
    @Autowired
    private TripService tripService;
    @Autowired
    private BusService busService;
//    @Autowired
//    private ticketSer
    @Autowired
    private Environment env;
    
    @ModelAttribute
    public void commonAtt(Model model) {
    }
    
    @RequestMapping("/listTrip")
    public String list(Model model,
            @RequestParam Map<String, String> params) {
        
        Map<String, String> paramsRoute = new HashMap<>();
        paramsRoute.put("origin", "Sai Gon");
        paramsRoute.put("destination", "Da");
        
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.tripService.countTrip();
        model.addAttribute("counter", Math.ceil(count*1.0/pageSize));
        
        Map<String, String> paramsTrip = new HashMap<>();
//        Ép nó lấy 1 cái thôi
//        String x = this.routeService.getRoutes(paramsRoute).get(0).getId().toString();
//        paramsTrip.put("tripId", "1");
        model.addAttribute("trip", this.tripService.getTrips(params));

        return "listTrip";
    }
    
    @GetMapping("/handleTrip")
    public String add(Model model) {
        model.addAttribute("trip", new Trip());
        model.addAttribute("routes", this.routeService.getRoutes(null));
        model.addAttribute("buses", this.busService.getBuses(null));
        return "handleTrip";
    }
    
    @GetMapping("/handleTrip/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("trip", this.tripService.getTripById(id));
        model.addAttribute("routes", this.routeService.getRoutes(null));
        model.addAttribute("buses", this.busService.getBuses(null));

        return "handleTrip";
    }
    
    @PostMapping("/handleTrip")
    public String add(@ModelAttribute(value = "trip") @Valid Trip t,
                        BindingResult rs) {
        if(!rs.hasErrors())
            if(this.tripService.addOrUpdateTrip(t) == true)
                return "redirect:/admin/listTrip";
        return "handleTrip";
    }
    
    @PostMapping("/setPriceTrip")
    public String updatePrice(@RequestParam Map<String, String> params, Model model) {
//        params.put("setOffDay", "08-12-2023");
//        params.put("endTime", "08-28-2023");
//        params.put("cost", "1.4");
        System.out.printf("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n%s\n%s\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^",
                params.get("cost"), params.get("endTime"));

        if(this.tripService.setHolidayCost(params)<0) {
            model.addAttribute("errMsg", "Something's wrong! try again later.");
            return "setPriceTrip"; 
        }
        model.addAttribute("trip", this.tripService.getTrips(null));

        return "listTrip";
    }
    
    @GetMapping("/setPriceTrip")
    public String updatePrice() {
        return "setPriceTrip";
    }
    
//    @GetMapping("/handleTickets/{tripId}")
//    public String handleTickets(Model model, @PathVariable(value = "tripId") int tripId ) {
//        model.addAttribute("tripId", tripId);
//        return "handleTickets";
//    }
//    
//    @PostMapping("/handleTickets")
//    public String handleTickets(@RequestParam(value="selectedValue") String[] seats,
//                                @RequestParam(value="tripId") int tripId) {
////        if(this.seatService.addOrUpdateSeat(seats, busId))
////            return "redirect:/admin/listTrip";
//        return "handleTickets";
//    }
}
