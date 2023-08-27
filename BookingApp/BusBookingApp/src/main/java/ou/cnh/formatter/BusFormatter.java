/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.formatter;

import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;
import ou.cnh.pojo.Bus;

/**
 *
 * @author zedmo
 */
public class BusFormatter implements Formatter<Bus>{

    @Override
    public String print(Bus bus, Locale locale) {
        return String.valueOf(bus.getId());
    }

    @Override
    public Bus parse(String busId, Locale locale) throws ParseException {
        return new Bus(Integer.parseInt(busId));
    }
    
}
