/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.formatter;

import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;
import ou.cnh.pojo.Role;

/**
 *
 * @author zedmo
 */
public class RoleFormatter implements Formatter<Role>{

    @Override
    public String print(Role role, Locale locale) {
        return String.valueOf(role.getId());
    }

    @Override
    public Role parse(String roleId, Locale locale) throws ParseException {
        return new Role(Integer.parseInt(roleId));
    }
    
}
