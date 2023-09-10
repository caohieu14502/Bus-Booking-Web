/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.repository.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ou.cnh.pojo.Ticket;
import ou.cnh.repository.TicketRepository;
import ou.cnh.repository.UserRepository;

/**
 *
 * @author zedmo
 */
@Repository
@Transactional
public class TicketRepositoryImpl implements TicketRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<Ticket> getTickets(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Ticket> q = b.createQuery(Ticket.class);
        
        Root root = q.from(Ticket.class);
        q.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        
        if (params != null) {
            String tripId = params.get("tripId");
            if(tripId != null && !tripId.isEmpty()) 
                predicates.add(b.equal(root.get("tripId"), Integer.valueOf(tripId)));
            
            String email = params.get("email");
            System.out.println(email);
            if(email != null && !email.isEmpty()) {
                predicates.add(b.like(root.get("billId").get("userId").get("email"), String.format("%%%s%%", email)));
                predicates.add(b.greaterThanOrEqualTo(root.get("setOffDay"), new Date()));
            }
        
            String myTrip = params.get("myTickets");
            if( myTrip!= null) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                predicates.add(b.equal(root.get("billId").get("userId"), this.userRepo.getUserByMail(authentication.getName()).getId()));
                
                if(myTrip.equals("past")) {
                    predicates.add(b.lessThanOrEqualTo(root.get("tripId").get("setOffDay"), new Date()));
                    predicates.add(b.lessThanOrEqualTo(root.get("tripId").get("setOffTime"), new Date()));
                }
                else if(myTrip.equals("future"))
                    predicates.add(b.greaterThan(root.get("tripId").get("setOffDay"), new Date()));
            }
        }
        
        q.where(predicates.toArray(Predicate[]::new))
                .orderBy(b.asc(root.get("seatId").get("seatRowPos")), b.asc(root.get("seatId").get("seatColPos")));
        Query query = s.createQuery(q);
        
        return query.getResultList();
    }

    @Override
    public Ticket getTicketById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Ticket.class, id);
    }

    @Override
    public boolean addOrUpdateTicket(Ticket t) {
                Session s = this.factory.getObject().getCurrentSession();
        try {
            if (t.getId() == null)
                s.save(t);
            else
                s.update(t);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
