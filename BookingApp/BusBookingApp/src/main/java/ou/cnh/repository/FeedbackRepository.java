/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.repository;

import java.util.List;
import java.util.Map;
import ou.cnh.pojo.Feedback;

/**
 *
 * @author zedmo
 */
public interface FeedbackRepository {
    List<Feedback> getFeedbacks(int id);
    Feedback addOrUpdateFeedback(Feedback fb);
}
