/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ou.cnh.pojo.User;
import ou.cnh.repository.RoleRepository;
import ou.cnh.repository.UserRepository;
import ou.cnh.service.RoleService;
import ou.cnh.service.UserService;

/**
 *
 * @author zedmo
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = this.userRepo.getUserByMail(email);
        if (u==null)
            throw new UsernameNotFoundException("Invalid Email");
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getRoleId().getRoleName()));
        return new org.springframework.security.core.userdetails.User(
                u.getEmail(), u.getPassword(), authorities);
    }

    @Override
    public boolean addOrUpdateUser(User u) {
        if(u.getId() == null){
            String password = u.getPassword();  
            u.setPassword(this.passwordEncoder.encode(password));
            u.setActive(Boolean.FALSE);
            //Xét Client User ở đây
            //if client is register then
            //u.setRole(role.getRole==Client);
        }
        if(!u.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(u.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.userRepo.addOrUpdateUser(u);
    }
    
//    https://www.baeldung.com/registration-with-spring-mvc-and-spring-security

    @Override
    public List<User> getUsers(Map<String, String> params) {
       return this.userRepo.getUsers(params);
    }

    @Override
    public User getUserById(int id) {
        return this.userRepo.getUserById(id);
    }

    @Override
    public boolean deleteUser(int id) {
        return this.userRepo.deleteUser(id);
    }

    @Override
    public boolean lockOrUnlock(int id) {
        return this.userRepo.lockOrUnlockUser(id);
    }

    @Override
    public User getUserByMail(String mail) {
        return this.userRepo.getUserByMail(mail);
    }

    @Override
    public boolean authUser(String mail, String password) {
        return this.userRepo.authUser(mail, password);
    }

    @Override
    public User addUser(Map<String, String> params, MultipartFile avatar) {
        User u = new User();
        u.setFirstName(params.get("firstName"));
        u.setLastName(params.get("lastName"));
        u.setEmail(params.get("email"));
        u.setPassword(this.passwordEncoder.encode(params.get("password")));
        Map<String, String> roleParams = new HashMap<>();
        roleParams.put("role", "ROLE_Client");
        u.setRoleId(roleRepo.getRoles(roleParams).get(0));
        if (!avatar.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(avatar.getBytes(), 
                        ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.userRepo.addUser(u);
        return u;
    }
}
