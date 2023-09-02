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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ou.cnh.pojo.Seat;
import ou.cnh.repository.SeatRepository;

/**
 *
 * @author zedmo
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class SeatRepositoryImpl implements SeatRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;


    @Override
    public List<Seat> getSeats(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Seat> q = b.createQuery(Seat.class);
        
        Root root = q.from(Seat.class);
        q.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        
        if (params != null) {
            
            String busId = params.get("busId");
            if(busId != null && !busId.isEmpty()) 
                predicates.add(b.equal(root.get("busId"), Integer.valueOf(busId)));
            
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
    public Seat getSeatById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Seat.class, id);
    }

    @Override
    public boolean addOrUpdateSeat(Seat seat) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (seat.getId() == null)
                s.save(seat);
            else
                s.update(seat);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
