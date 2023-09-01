/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ou.cnh.repository;

import java.util.List;
import java.util.Map;
import ou.cnh.pojo.User;

/**
 *
 * @author zedmo
 */
public interface UserRepository {
    User getUserByMail(String mail);
    List<User> getUsers(Map<String, String> params);
    boolean addOrUpdateUser(User u);
    User getUserById(int id);
    boolean deleteUser(int id);
    boolean lockOrUnlockUser(int id);
    boolean authUser(String mail, String password);
    User addUser(User user);
}
