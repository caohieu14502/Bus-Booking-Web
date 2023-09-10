/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ou.cnh.pojo.User;
import ou.cnh.repository.UserRepository;

/**
 *
 * @author zedmo
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public User getUserByMail(String mail) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findByEmail", User.class);
        q.setParameter("email",mail);
        List<User> u = q.getResultList();
        try {
            return u.get(0);
        } catch (IndexOutOfBoundsException ex){
            return null;
        }
    }

    @Override
    public List<User> getUsers(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);

        Root rUser = q.from(User.class);
        
        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            String phone = params.get("phoneNumber");
            if (phone != null && !phone.isEmpty())
                predicates.add(b.like(rUser.get("phoneNumber"), String.format("%%%s%%", phone)));

            String name = params.get("name");
            if (name != null && !name.isEmpty()) {
                Predicate firstName = b.like(rUser.get("firstName"), String.format("%%%s%%", name));
                Predicate lastName = b.like(rUser.get("lastName"), String.format("%%%s%%", name));
                
                predicates.add(b.or(firstName, lastName));
            }
            
            String mail = params.get("email");
            if (mail != null && !mail.isEmpty())
                predicates.add(b.like(rUser.get("email"), String.format("%%%s%%", mail)));
            
            String role = params.get("role");
            if (role != null && !role.isEmpty())
                predicates.add(b.equal(rUser.get("roleId"), Integer.valueOf(role)));
        }

        q.where(predicates.toArray(Predicate[]::new))
                .orderBy(b.asc(rUser.get("id")))
                .select(rUser);

        Query query = s.createQuery(q);
        
        if (params != null) {
            String p = params.get("page");
            if(p != null && !p.isEmpty()) {
                int page = Integer.parseInt(p);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
                query.setMaxResults(pageSize);
                query.setFirstResult((page-1)*pageSize);
            }
        }
        return query.getResultList();
    }

    @Override
    public boolean addOrUpdateUser(User u) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (u.getId() == null) {
                s.save(u);
            }
            else
                s.update(u);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(User.class, id);
    }

    @Override
    public boolean deleteUser(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        User u = this.getUserById(id);
        try {
            s.delete(u);
            return true;
        } catch(HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean lockOrUnlockUser(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        User u = this.getUserById(id);
        try {
            if(u.getActive())
                u.setActive(Boolean.FALSE);
            else
                u.setActive(Boolean.TRUE);
            s.update(u);
            return true;
        } catch(HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean authUser(String mail, String password) {
        User  u = this.getUserByMail(mail);
        System.out.printf("user: %s\nemail: %s",u,mail);
        if(u != null)
            return this.passEncoder.matches(password, u.getPassword());
        return false;
    }

    @Override
    public User addUser(User u) {
                Session s = this.factory.getObject().getCurrentSession();
        s.save(u);
        
        return u;
    }
}
