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
import ou.cnh.pojo.User;
import ou.cnh.service.RoleService;
import ou.cnh.service.UserService;

/**
 *
 * @author zedmo
 */
@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    
    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("roles", this.roleService.getRoles());

    }

    @RequestMapping("/listUser")
    public String list(Model model,
            @RequestParam Map<String, String> params) {

        model.addAttribute("users", this.userService.getUsers(params));
        return "listUser";
    }

    @GetMapping("/handleUser")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "handleUser";
    }

    @PostMapping("/handleUser")
    public String add(@ModelAttribute(value = "user") @Valid User u,
            BindingResult rs, Model model) {
        String errMsg = "";
        if (this.userService.getUserByMail(u.getEmail()) != null && u.getId() == null)
            errMsg = "Email đã tồn tại!!";
        else {
            if (!rs.hasErrors()) {
                if (this.userService.addOrUpdateUser(u) == true)
                    return "redirect:/admin/listUser";
                else
                    errMsg = "Đã có lỗi xảy ra! Vui lòng quay lại sau";
            }
        }

        model.addAttribute("errMsg", errMsg);
        return "handleUser";
    }

    @GetMapping("/handleUser/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("user", this.userService.getUserById(id));
        return "handleUser";
    }

}
