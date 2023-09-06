/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.repository.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
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
import ou.cnh.pojo.Route;
import ou.cnh.pojo.Trip;
import ou.cnh.repository.TripRepository;

/**
 *
 * @author zedmo
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class TripRepositoryImpl implements TripRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Resource(name = "simpleDateFormat")
//    @Autowired
    private SimpleDateFormat simpleDateFormat;
    @Resource(name = "setOffFormat")
//    @Autowired
    private SimpleDateFormat f2;
    @Autowired
    private Environment env;

    //Lúc nào cũng truyền vào Date hiện tại trở đi đổ ra cho client
    //Dùng Map khi có khả năng các tham số không cần truyền vào
    //lấy nhiều thông số khác sau khi join thì được để ở đây không ta?
    public List<Trip> getTrips(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Trip> q = b.createQuery(Trip.class);

        Root rTrip = q.from(Trip.class);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {

            String destination = params.get("destination");
            String orgin = params.get("origin");
            if (destination != null && !destination.isEmpty() && orgin != null && !orgin.isEmpty()) //                predicates.add(b.equal(rBusRoute.get("routeId"), Integer.valueOf(routeId)));
            {
                Root rRoute = q.from(Route.class);
                predicates.add(b.like(rRoute.get("origin").get("province"), String.format("%%%s%%", orgin)));
                predicates.add(b.like(rRoute.get("destination").get("province"), String.format("%%%s%%", destination)));
                predicates.add(b.equal(rRoute.get("id"), rTrip.get("routeId")));
            }

            //Lọc theo ngày xuất phát, theo giờ
            String fd = params.get("setOffDay");
            if (fd != null && !fd.isEmpty()) {
                try {
                    predicates.add(b.greaterThanOrEqualTo(rTrip.get("setOffDay"), simpleDateFormat.parse(fd)));
                } catch (ParseException ex) {
                    Logger.getLogger(TripRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            String routeId = params.get("routeId");
            if (routeId != null && !routeId.isEmpty())
                    predicates.add(b.greaterThanOrEqualTo(rTrip.get("routeId"), Integer.valueOf(routeId)));
        }

        q.where(predicates.toArray(Predicate[]::new))
                .orderBy(b.asc(rTrip.get("setOffDay")))
                .select(rTrip);

        Query query = s.createQuery(q);

        if (params != null) {

            String p = params.get("page");
            if (p != null && !p.isEmpty()) {
                int page = Integer.parseInt(p);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
                query.setMaxResults(pageSize);
                query.setFirstResult((page - 1) * pageSize);
            }
        }
        return query.getResultList();
    }

    //Thiết lập tăng hệ số giá tiền vào ngày lễ tết
    @Override
    public Integer setHolidayCost(Map<String, String> params) {
        //lấy ra tất cả các trip trong 1 khoảng thời gian SẮP TỚI, set all thuộc tính holliday_cost của tụi nó
        String setOff = params.get("setOffDay");
        String endTime = params.get("endTime");
        Float cost = Float.parseFloat(params.get("cost"));
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaUpdate<Trip> update = b.createCriteriaUpdate(Trip.class);

        Root rTrip = update.from(Trip.class);

        update.set("holidayCost", cost);
        try {
            update.where(b.between(rTrip.get("setOffDay"), simpleDateFormat.parse(setOff), simpleDateFormat.parse(endTime)));
        } catch (ParseException ex) {
            Logger.getLogger(TripRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s.createQuery(update).executeUpdate();
    }

    @Override
    public int countTrip() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT count(*) FROM Trip");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public Trip getTripById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        System.out.printf("^^^^\n%s\n^^^^\n", s.get(Trip.class, id));
        return s.get(Trip.class, id);
    }

    @Override
    public boolean addOrUpdateTrip(Trip t) {
        try {
            t.setSetOffTime(f2.parse(t.getSetOffTimeString()));
        } catch (ParseException ex) {
            Logger.getLogger(TripRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime  dt = LocalDate.parse(t.getSetOffDayString(), fmt).atStartOfDay();
        
        Date in = new Date();
        Date out = Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());
        t.setSetOffDay(out);
//        System.out.printf("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n%s\n%s\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^", t.getSetOffDay(), t.getSetOffTime());
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (t.getId() == null) {
                s.save(t);
            } else {
                s.update(t);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTrip(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Trip t = this.getTripById(id);
        try {
            s.delete(t);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public int addTrip(Trip t) {
        Session s = this.factory.getObject().getCurrentSession();
        try{
            return (int) s.save(t);
        } catch(HibernateException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

}
