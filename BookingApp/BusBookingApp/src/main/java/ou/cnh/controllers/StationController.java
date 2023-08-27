/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.controllers;

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
import ou.cnh.pojo.Station;
import ou.cnh.service.RouteService;
import ou.cnh.service.StationService;

/**
 *
 * @author zedmo
 */
@Controller
@RequestMapping("/admin")
public class StationController {
    @Autowired
    private RouteService routeService;
    @Autowired
    private StationService stationService;
    @Autowired
    private Environment env;
    
    @RequestMapping("/listStation")
    public String list(Model model,
            @RequestParam Map<String, String> params) {
        
        model.addAttribute("station", this.stationService.getStations(params));
        return "listStation";
    }
    
    @GetMapping("/handleStation")
    public String add(Model model) {
        model.addAttribute("station", new Station());
        return "handleStation";
    }
    
    @GetMapping("/handleStation/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("station", this.stationService.getStationById(id));
        return "handleStation";
    }
    
    @PostMapping("/handleStation")
    public String add(@ModelAttribute(value = "station") @Valid Station s,
                        BindingResult rs) {
        if(!rs.hasErrors())
            if(this.stationService.addOrUpdateStation(s) == true)
                return "redirect:/admin/listStation";
        return "handleStation";
    }
}
