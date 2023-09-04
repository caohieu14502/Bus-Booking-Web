/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.repository.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ou.cnh.pojo.Feedback;
import ou.cnh.repository.FeedbackRepository;

/**
 *
 * @author zedmo
 */
@Repository
@Transactional
public class FeedbackRepositoryImpl implements FeedbackRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    @Resource(name = "simpleDateFormat")
    private SimpleDateFormat simpleDateFormat;

    @Override
    public List<Feedback> getFeedbacks(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Feedback> q = b.createQuery(Feedback.class);

        Root rFeedback = q.from(Feedback.class);

        q.where(b.equal(rFeedback.get("tripId"), id))
                .orderBy(b.desc(rFeedback.get("createdAt")))
                .select(rFeedback);

        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public Feedback addOrUpdateFeedback(Feedback fb) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (fb.getId() == null) {
                fb.setCreatedAt(new Date());
                s.save(fb);
            } else {
                fb.setUpdatedAt(new Date());
                s.update(fb);
            }
            return fb;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}
