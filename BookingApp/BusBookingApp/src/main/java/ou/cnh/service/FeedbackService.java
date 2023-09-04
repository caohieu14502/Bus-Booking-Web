/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ou.cnh.service;

import java.util.List;
import java.util.Map;
import ou.cnh.pojo.Feedback;

/**
 *
 * @author zedmo
 */
public interface FeedbackService {
    List<Feedback> getFeedbacks(int id);
    Feedback addOrUpdateFeedback(Feedback fb);
}
