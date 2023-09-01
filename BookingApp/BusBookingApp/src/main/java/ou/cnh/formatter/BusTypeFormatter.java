/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.formatter;

import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;
import ou.cnh.pojo.BusType;

/**
 *
 * @author zedmo
 */
public class BusTypeFormatter implements Formatter<BusType>{

    @Override
    public String print(BusType busType, Locale locale) {
              return String.valueOf(busType.getId());
    }

    @Override
    public BusType parse(String busTypeId, Locale locale) throws ParseException {
        return new BusType(Integer.parseInt(busTypeId));
    }
}
