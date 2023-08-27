/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.cnh.pojo.Station;
import ou.cnh.repository.StationRepository;
import ou.cnh.service.StationService;

/**
 *
 * @author zedmo
 */
@Service
public class StationServiceImpl implements StationService{
    
    @Autowired
    private StationRepository stationRepo;
    @Autowired
    private Cloudinary cloudinary;

    
    @Override
    public List<Station> getStations(Map<String, String> params) {
        return this.stationRepo.getStations(params);
    }

    @Override
    public int countStation() {
        return this.stationRepo.countStation();
    }

    @Override
    public boolean addOrUpdateStation(Station s) {
        if(!s.getFile().isEmpty() && s.getPicture() == null) {
            try {
                Map res = this.cloudinary.uploader().upload(s.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                s.setPicture(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(StationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.stationRepo.addOrUpdateStation(s);
    }

    @Override
    public Station getStationById(int id) {
        return this.stationRepo.getStationById(id);
    }

    @Override
    public boolean deleteStation(int id) {
         return this.stationRepo.deleteStation(id);
    }
}
