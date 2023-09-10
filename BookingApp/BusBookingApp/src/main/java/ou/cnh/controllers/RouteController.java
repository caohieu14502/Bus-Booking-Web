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
import ou.cnh.pojo.Route;
import ou.cnh.service.RouteService;
import ou.cnh.service.StationService;

/**
 *
 * @author zedmo
 */
@Controller
@RequestMapping("/admin")
public class RouteController {
    @Autowired
    private RouteService routeService;
    @Autowired
    private StationService stationService;
    @Autowired
    private Environment env;
    
    @ModelAttribute
    public void commonRouteAtt(Model model) {
        model.addAttribute("places", this.stationService.getStations(null));

    }
    
    @RequestMapping("/listRoute")
    public String index(Model model,
            @RequestParam Map<String, String> params) {
        
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.routeService.countRoute();
        model.addAttribute("counter", Math.ceil(count*1.0/pageSize));
        
        model.addAttribute("route", this.routeService.getRoutes(params));
        return "listRoute";
    }
    
    @GetMapping("/handleRoute")
    public String add(Model model) {
        model.addAttribute("route", new Route());

        return "handleRoute";
    }
    
    @GetMapping("/handleRoute/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("route", this.routeService.getRouteById(id));

        return "handleRoute";
    }
    
    @PostMapping("/handleRoute")
    public String add(@ModelAttribute(value = "route") @Valid Route r,
                        BindingResult rs, Model model) {
        if(!rs.hasErrors()) {
            if(this.routeService.isExistRoute(r))
                model.addAttribute("existErr", "Tuyến đi này đã tồn tại");
            else if(this.routeService.addOrUpdateRoute(r) == true)
                return "redirect:/admin/listRoute";
        }
        return "handleRoute";
    }
}
