/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.repository.impl;

import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ou.cnh.pojo.Role;
import ou.cnh.repository.RoleRepository;

/**
 *
 * @author zedmo
 */
@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Role> getRoles(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q;
        if(params != null) {
            String role = params.get("role");
            q = s.createNamedQuery("Role.findByRoleName", Role.class);
            q.setParameter("roleName", role);
        }
        else
            q = s.createQuery("FROM Role");

        return q.getResultList();
    }
    
}
