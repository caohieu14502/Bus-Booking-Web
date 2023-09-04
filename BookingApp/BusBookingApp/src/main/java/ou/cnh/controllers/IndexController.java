/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.controllers;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ou.cnh.service.StatsService;

/**
 *
 * @author zedmo
 */
@Controller
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class IndexController {
    @Autowired
    private StatsService statsService;
    
    
    //Luôn trả các model ở method sau cho tất cả các method khác trong IndexController
    @ModelAttribute
    public void commonAtt(Model model,
        @RequestParam Map<String, String> params) {
        String pageActive = params.get("page");
        if(pageActive != null)
            model.addAttribute("pageActive", Integer.parseInt(params.get("page")));
    }
    
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    
    
    @RequestMapping("/dashboard")
    public String dashboard(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("statsRevenue", this.statsService.statesRevenue(params));
        model.addAttribute("statsTicketCount", this.statsService.countBookedTicketByRoutes());
        model.addAttribute("statsRouteCount", this.statsService.countTripByRoutes());
        model.addAttribute("statsRevenueTotal", this.statsService.statesRevenueTotal(params));
        
        String type = params.get("type");
        if(type != null && !type.isEmpty())
            model.addAttribute("type", type.substring(0, 1).toUpperCase() + type.substring(1));

        return "dashboard";
    }
    
}
