/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.repository.impl;

import java.util.Date;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ou.cnh.pojo.Bill;
import ou.cnh.pojo.Cart;
import ou.cnh.pojo.Ticket;
import ou.cnh.repository.BillRepository;
import ou.cnh.repository.TicketRepository;
import ou.cnh.repository.UserRepository;

/**
 *
 * @author zedmo
 */
@Repository
public class BillRepositoryImpl implements BillRepository {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private TicketRepository ticketRepo;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addBill(Map<String, Cart> carts) {
        Session s = this.factory.getObject().getCurrentSession();
        Bill bill = new Bill();

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            bill.setUserId(this.userRepo.getUserByMail(authentication.getName()));
            bill.setCreatedAt(new Date());
            s.save(bill);
            
            for (Cart c : carts.values()) {
                Ticket t = this.ticketRepo.getTicketById(c.getId());
                t.setBillId(bill);
                t.setIsAvailable(false);
                s.save(t);
            }
            
//            https://stackoverflow.com/questions/19468572/spring-mvc-why-not-able-to-use-requestbody-and-requestparam-together
            // Dung request body va request param de lay them thong tin Nguoi mua Ticket voi Bill Offline
            return true;
        } catch (HibernateException ex) {
            return false;
        }

    }
}
