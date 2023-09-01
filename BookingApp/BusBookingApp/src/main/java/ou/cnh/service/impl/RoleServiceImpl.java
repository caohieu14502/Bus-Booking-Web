/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.cnh.pojo.Role;
import ou.cnh.repository.RoleRepository;
import ou.cnh.service.RoleService;

/**
 *
 * @author zedmo
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public List<Role> getRoles(Map<String, String> params) {
       return this.roleRepository.getRoles(params);
    }
    
}
