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
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ou.cnh.pojo.Bus;
import ou.cnh.repository.BusRepository;

/**
 *
 * @author zedmo
 */
@Repository
@Transactional
public class BusRepositoryImpl implements BusRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Bus> getBuses(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Bus> q = b.createQuery(Bus.class);
        
        Root root = q.from(Bus.class);
        q.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        
        if (params != null) {
            
            String plate = params.get("plate");
            if(plate != null && !plate.isEmpty()) 
                predicates.add(b.like(root.get("plate"), String.format("%%%s%%", plate)));
            
            String busType = params.get("busType");
            if(busType != null && !busType.isEmpty())
                predicates.add(b.equal(root.get("busType"), Integer.parseInt(busType)));
            
            String seat = params.get("seats");
                if(seat != null && !seat.isEmpty()) 
                    predicates.add(b.equal(root.get("numberOfSeats"), Integer.parseInt(seat)));
        }
        
        q.where(predicates.toArray(Predicate[]::new))
                .orderBy(b.asc(root.get("id")));
        Query query = s.createQuery(q);
        
        if (params != null) {
            String p = params.get("page");
            if(p != null && !p.isEmpty()) {
                int page = Integer.parseInt(p);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
                 
                query.setMaxResults(pageSize);
                query.setFirstResult((page - 1) * pageSize);
            }
        }
        
        return query.getResultList();
    }

    @Override
    public int countBus() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT count(*) FROM Bus");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateBus(Bus r) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (r.getId() == null)
                s.save(r);
            else
                s.update(r);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Bus getBusById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Bus.class, id);
    }

    @Override
    public boolean deleteBus(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Bus b = this.getBusById(id);
        try {
            s.delete(b);
            return true;
        } catch(HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
