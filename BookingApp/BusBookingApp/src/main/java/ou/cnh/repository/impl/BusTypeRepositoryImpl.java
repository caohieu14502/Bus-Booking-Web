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
import ou.cnh.pojo.BusType;
import ou.cnh.repository.BusTypeRepository;

/**
 *
 * @author zedmo
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class BusTypeRepositoryImpl implements BusTypeRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<BusType> getBusTypes(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<BusType> q = b.createQuery(BusType.class);

        Root root = q.from(BusType.class);
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
    public boolean addOrUpdateBusType(BusType bt) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (bt.getId() == null)
                s.save(bt);
            else
                s.update(bt);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public BusType getBusTypeById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(BusType.class, id);
    }

    @Override
    public boolean deleteBusType(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        BusType r = this.getBusTypeById(id);
        try {
            s.delete(r);
            return true;
        } catch(HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
