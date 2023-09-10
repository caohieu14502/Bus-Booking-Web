/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.controllers;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ou.cnh.pojo.Bus;
import ou.cnh.service.BusService;
import ou.cnh.service.BusTypeService;
import ou.cnh.service.SeatService;

/**
 *
 * @author zedmo
 */
@Controller
@RequestMapping("/admin")
public class BusController {
    @Autowired
    private BusService busService;
    @Autowired
    private BusTypeService busTypeService;
    @Autowired
    private SeatService seatService;
    
    @RequestMapping("/listBus")
    public String list(Model model,
            @RequestParam Map<String, String> params) {
        
        model.addAttribute("bus", this.busService.getBuses(params));
        return "listBus";
    }
    @GetMapping("/handleBus")
    public String add(Model model) {
        model.addAttribute("bus", new Bus());
        model.addAttribute("busTypes", this.busTypeService.getBusTypes(null));

        return "handleBus";
    }
    
    @GetMapping("/handleBus/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("bus", this.busService.getBusById(id));
        model.addAttribute("busTypes", this.busTypeService.getBusTypes(null));

        return "handleBus";
    }
    
    @PostMapping("/handleBus")
    public String add(@ModelAttribute(value = "bus") @Valid Bus b,
                        BindingResult rs, Model model) {
        if(!rs.hasErrors()) {
            if(this.busService.isExistBus(b))
                model.addAttribute("existErr", "Biển số xe này đã tồn tại");
            else if(this.busService.addOrUpdateBus(b) == true)
                return "redirect:/admin/listBus";
        }
        return "handleBus";
    }
   
    @GetMapping("/handleSeats/{busId}")
    public String handleSeats(Model model, @PathVariable(value = "busId") int busId ) {
        model.addAttribute("busId", busId);
        return "handleSeats";
    }
    
    @PostMapping("/handleSeats")
    public String handleSeats(@RequestParam(value="selectedValue") String[] seats,
                                @RequestParam(value="busId") int busId) {
        if(this.seatService.addOrUpdateSeat(seats, busId))
            return "redirect:/admin/listBus";
        return "handleSeats";
    }
}
