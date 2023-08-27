/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.formatter;

import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;
import ou.cnh.pojo.Station;

/**
 *
 * @author zedmo
 */
public class StationFormatter implements Formatter<Station>{

    @Override
    public String print(Station place, Locale locale) {
        return String.valueOf(place.getId());
    }

    @Override
    public Station parse(String placeId, Locale locale) throws ParseException {
        return new Station(Integer.parseInt(placeId));
    }
    
}
