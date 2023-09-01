/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ou.cnh.service;

import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;
import ou.cnh.pojo.User;

/**
 *
 * @author zedmo
 */
public interface UserService extends UserDetailsService{
    boolean addOrUpdateUser(User u);
    List<User> getUsers(Map<String, String> params);
    User getUserById(int id);
    boolean deleteUser(int id);
    boolean lockOrUnlock(int id);
    User getUserByMail(String mail);
    boolean authUser(String username, String password);
    User addUser(Map<String, String> params, MultipartFile avatar);

}
