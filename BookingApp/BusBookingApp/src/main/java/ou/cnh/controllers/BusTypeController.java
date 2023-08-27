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
import ou.cnh.pojo.BusType;
import ou.cnh.service.BusTypeService;

/**
 *
 * @author zedmo
 */
@Controller
@RequestMapping("/admin")
public class BusTypeController {
    @Autowired
    private BusTypeService busTypeService;
    
    @RequestMapping("/listBusType")
    public String list(Model model,
            @RequestParam Map<String, String> params) {
        
        model.addAttribute("busType", this.busTypeService.getBusTypes(params));
        return "listBusType";
    }
    
    @GetMapping("/handleBusType")
    public String add(Model model) {
        model.addAttribute("busType", new BusType());

        return "handleBusType";
    }
    
    @GetMapping("/handleBusType/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("busType", this.busTypeService.getBusTypeById(id));

        return "handleBusType";
    }
    
    @PostMapping("/handleBusType")
    public String add(@ModelAttribute(value = "bus") @Valid BusType b,
                        BindingResult rs) {
        if(!rs.hasErrors())
            if(this.busTypeService.addOrUpdateBusType(b) == true)
                return "redirect:/admin/listBusType";
        return "handleBusType";
    }
}
