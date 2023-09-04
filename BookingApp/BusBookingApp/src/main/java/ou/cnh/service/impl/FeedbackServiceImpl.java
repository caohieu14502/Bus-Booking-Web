/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ou.cnh.pojo.Feedback;
import ou.cnh.pojo.User;
import ou.cnh.repository.FeedbackRepository;
import ou.cnh.repository.UserRepository;
import ou.cnh.service.FeedbackService;

/**
 *
 * @author zedmo
 */
@Service
public class FeedbackServiceImpl implements FeedbackService{
    
    @Autowired
    private FeedbackRepository feedbackRepo;
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<Feedback> getFeedbacks(int id) {
        return this.feedbackRepo.getFeedbacks(id);
    }

    @Override
    public Feedback addOrUpdateFeedback(Feedback fb) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userRepo.getUserByMail(authentication.getName());
        fb.setUserId(u);
        return this.feedbackRepo.addOrUpdateFeedback(fb);
    }
    
}
