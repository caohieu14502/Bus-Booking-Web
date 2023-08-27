/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.repository.impl;

import java.util.ArrayList;
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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ou.cnh.pojo.Route;
import ou.cnh.repository.RouteRepository;

/**
 *
 * @author zedmo
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class RouteRepositoryImpl implements RouteRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    //Tài xế/Admin sẽ dùng hàm này nhiều để mà set up các chuyến đi
    //Sử dụng để xuất các chuyến đi có nhiều lần đi nhất
    public List<Route> getRoutes(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Route> q = b.createQuery(Route.class);

        Root root = q.from(Route.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {

            String ori = params.get("origin");
            if (ori != null && !ori.isEmpty()) {
                predicates.add(b.like(root.get("origin").get("province"), String.format("%%%s%%", ori)));
            }

            String des = params.get("destination");
            if (des != null && !des.isEmpty()) {
                predicates.add(b.like(root.get("destination").get("province"), String.format("%%%s%%", des)));
            }

        }
        q.where(predicates.toArray(Predicate[]::new))
                .orderBy(b.asc(root.get("id")));
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

    @Override
    public int countRoute() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT count(*) FROM Route");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateRoute(Route r) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (r.getId() == null) {
                s.save(r);
            } else {
                s.update(r);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public Route getRouteById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Route.class, id);
    }

    @Override
    public boolean deleteRoute(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Route r = this.getRouteById(id);
        try {
            s.delete(r);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
