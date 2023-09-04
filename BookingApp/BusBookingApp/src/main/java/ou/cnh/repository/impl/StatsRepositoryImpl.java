/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.repository.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ou.cnh.pojo.Route;
import ou.cnh.pojo.Ticket;
import ou.cnh.pojo.Trip;
import ou.cnh.repository.StatsRepository;

/**
 *
 * @author zedmo
 */
@Repository
@Transactional
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Resource(name = "simpleDateFormat")
    private SimpleDateFormat simpleDateFormat;

    public List<Object[]> countTripByRoutes() {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rTrip = q.from(Trip.class);
        Root rRoute = q.from(Route.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(b.equal(rTrip.get("routeId"), rRoute.get("id")));

        q.where(predicates.toArray(Predicate[]::new));

        q.multiselect(rRoute.get("id"), rRoute.get("origin").get("province"), rRoute.get("destination").get("province"), b.count(rTrip.get("id")));
        q.groupBy(rRoute.get("id"));

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    public List<Object[]> countBookedTicketByRoutes() {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rTrip = q.from(Trip.class);
        Root rRoute = q.from(Route.class);
        Root rTicket = q.from(Ticket.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(b.equal(rTrip.get("routeId"), rRoute.get("id")));
        predicates.add(b.equal(rTicket.get("tripId"), rTrip.get("id")));
        predicates.add(b.isNotNull(rTicket.get("billId")));

        q.where(predicates.toArray(Predicate[]::new));

        q.multiselect(rRoute.get("id"), rRoute.get("origin").get("province"), rRoute.get("destination").get("province"), b.count(rTrip.get("id")));
        q.groupBy(rRoute.get("id"));

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    public List<Object[]> statesRevenue(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rTicket = q.from(Ticket.class);
        Root rTrip = q.from(Trip.class);
        Root rRoute = q.from(Route.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rTicket.get("tripId"), rTrip.get("id")));
        predicates.add(b.equal(rTrip.get("routeId"), rRoute.get("id")));
        predicates.add(b.isNotNull(rTicket.get("billId")));

        String fd = params.get("fromDate");
        if (fd != null && !fd.isEmpty()) {
            try {
                predicates.add(b.greaterThanOrEqualTo(rTrip.get("setOffDay"), simpleDateFormat.parse(fd)));
            } catch (ParseException ex) {
                Logger.getLogger(StatsRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String td = params.get("toDate");
        if (td != null && !td.isEmpty()) {
            try {
                predicates.add(b.lessThanOrEqualTo(rTrip.get("setOffDay"), simpleDateFormat.parse(td)));
            } catch (ParseException ex) {
                Logger.getLogger(StatsRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String year = params.get("year");
        if (year != null && !year.isEmpty()) {
            //Trích xuất(lấy) YEAR từ createdDate dạng Date của rOrder ròi so sánh với param
            predicates.add(b.equal(b.function("YEAR", Integer.class, rTrip.get("setOffDay")), Integer.parseInt(year)));

            String quarter = params.get("quarter");
            if (quarter != null && !quarter.isEmpty()) {
                predicates.add(b.equal(b.function("QUARTER", Integer.class, rTrip.get("setOffDay")), Integer.parseInt(quarter)));
            }
        }

        q.where(predicates.toArray(Predicate[]::new));

        q.multiselect(rRoute.get("id"), rRoute.get("origin").get("province"), rRoute.get("destination").get("province"), b.sum(rTicket.get("price")));
        q.groupBy(rRoute.get("id"));

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    public List<Object[]> statesRevenueTotal(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rTicket = q.from(Ticket.class);
        Root rTrip = q.from(Trip.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rTicket.get("tripId"), rTrip.get("id")));
        predicates.add(b.isNotNull(rTicket.get("billId")));

        String type = params.get("type");
        if (type != null && !type.isEmpty()) {
            if (type.equals("year")) {
                q.multiselect(b.function("YEAR", Integer.class, rTrip.get("setOffDay")), b.sum(rTicket.get("price")));
                q.groupBy(b.function("YEAR", Integer.class, rTrip.get("setOffDay")));
            } else if (type.equals("quarter")) {
                String year = params.get("yearType");
                if (year != null && !year.isEmpty()) {
                    predicates.add(b.equal(b.function("YEAR", Integer.class, rTrip.get("setOffDay")), 2023));
                }
                q.multiselect(b.function("QUARTER", Integer.class, rTrip.get("setOffDay")), b.sum(rTicket.get("price")));
                q.groupBy(b.function("QUARTER", Integer.class, rTrip.get("setOffDay")));
            }
        } else {
            predicates.add(b.equal(b.function("YEAR", Integer.class, rTrip.get("setOffDay")), 2023));
            q.multiselect(b.function("MONTH", Integer.class, rTrip.get("setOffDay")), b.sum(rTicket.get("price")));
            q.groupBy(b.function("MONTH", Integer.class, rTrip.get("setOffDay")));
        }

        q.where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);
        return query.getResultList();
    }
}
