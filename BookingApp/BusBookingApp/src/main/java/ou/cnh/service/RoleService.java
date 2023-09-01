/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ou.cnh.service;

import java.util.List;
import java.util.Map;
import ou.cnh.pojo.Role;

/**
 *
 * @author zedmo
 */
public interface RoleService {
    List<Role> getRoles(Map<String, String> params);
}
