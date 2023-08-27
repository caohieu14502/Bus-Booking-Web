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
import ou.cnh.pojo.Station;
import ou.cnh.repository.StationRepository;

/**
 *
 * @author zedmo
 */
@Repository
@Transactional
public class StationRepositoryImpl implements StationRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Station> getStations(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Station> q = b.createQuery(Station.class);

        Root root = q.from(Station.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            String province = params.get("province");
            if (province != null && !province.isEmpty()) {
                predicates.add(b.like(root.get("province"), String.format("%%%s%%", province)));
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
    public int countStation() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT count(*) FROM Station");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateStation(Station sta) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (sta.getId() == null)
                s.save(sta);
            else
                s.update(sta);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Station getStationById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Station.class, id);
    }

    @Override
    public boolean deleteStation(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Station r = this.getStationById(id);
        try {
            s.delete(r);
            return true;
        } catch(HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
