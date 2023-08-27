///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package ou.cnh.repository.impl;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.persistence.Query;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import ou.cnh.pojo.Bus;
//import ou.cnh.pojo.Route;
//import ou.cnh.pojo.Trip;
//import ou.cnh.repository.StatsRepository;
//
///**
// *
// * @author zedmo
// */
//@Repository
//@Transactional
//public class StatsRepositoryImpl implements StatsRepository {
//
//    @Autowired
//    private LocalSessionFactoryBean factory;
//    @Autowired
//    private SimpleDateFormat f;
//
//    public List<Object[]> countTripByRoutes() {
//        Session s = this.factory.getObject().getCurrentSession();
//
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//
//        Root rTrip = q.from(Trip.class);
////        Root rBusRoute = q.from(BusRoute.class);
//        Root rRoute = q.from(Route.class);
//        
//        List<Predicate> predicates = new ArrayList<>();
//
////        predicates.add(b.equal(rTrip.get("busRouteId"), rBusRoute.get("id")));
////        predicates.add(b.equal(rBusRoute.get("routeId"), rRoute.get("id")));
//
//        q.where(predicates.toArray(Predicate[]::new));
//
//        q.multiselect(rRoute.get("id"), rRoute.get("name"), b.count(rTrip.get("id")));
//        q.groupBy(rRoute.get("id"));
//
//        Query query = s.createQuery(q);
//        return query.getResultList();
//    }
//
//    public List<Object[]> statesRevenue(Map<String, String> params) {
//        Session s = this.factory.getObject().getCurrentSession();
//
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//
//        Root rProduct = q.from(Route.class);
////        Root rOrderDetails = q.from(BusRoute.class);
//        Root rOrder = q.from(Bus.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//
////        predicates.add(b.equal(rOrderDetails.get("productId"), rProduct.get("id")));
////        predicates.add(b.equal(rOrderDetails.get("orderId"), rOrder.get("id")));
//
//        String fd = params.get("fromDate");
//        if (fd != null && !fd.isEmpty()) {
//            try {
//                predicates.add(b.greaterThanOrEqualTo(rOrder.get("createdDate"), f.parse(fd)));
//            } catch (ParseException ex) {
//                Logger.getLogger(StatsRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        String td = params.get("toDate");
//        if (td != null && !td.isEmpty()) {
//            try {
//                predicates.add(b.lessThanOrEqualTo(rOrder.get("createdDate"), f.parse(td)));
//            } catch (ParseException ex) {
//                Logger.getLogger(StatsRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        String quarter = params.get("quarter");
//        if (quarter != null && !quarter.isEmpty()) {
//            String year = params.get("year");
//            if (year != null && !year.isEmpty()) {
//                //Trích xuất(lấy) YEAR từ createdDate dạng Date của rOrder ròi so sánh với param
//                predicates.add(b.equal(b.function("YEAR", Integer.class, rOrder.get("createdDate")), Integer.parseInt(year)));
//                //
//                predicates.add(b.equal(b.function("QUARTER", Integer.class, rOrder.get("createdDate")), Integer.parseInt(quarter)));
//            }
//        }
//
//        q.where(predicates.toArray(Predicate[]::new));
//
////        q.multiselect(rProduct.get("id"), rProduct.get("name"), b.prod(rOrderDetails.get("unitPrice"), rOrderDetails.get("num")));
//        q.groupBy(rProduct.get("id"));
//
//        Query query = s.createQuery(q);
//        return query.getResultList();
//    }
//}
