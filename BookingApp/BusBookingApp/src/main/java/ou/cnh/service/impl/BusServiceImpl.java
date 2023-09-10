/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.cnh.pojo.Bus;
import ou.cnh.repository.BusRepository;
import ou.cnh.service.BusService;

/**
 *
 * @author zedmo
 */
@Service
public class BusServiceImpl implements BusService{
    
    @Autowired
    private BusRepository busRepo;

    @Override
    public List<Bus> getBuses(Map<String, String> params) {
        return this.busRepo.getBuses(params);
    }

    @Override
    public int countBus() {
        return this.busRepo.countBus();
    }

    @Override
    public boolean addOrUpdateBus(Bus b) {
        return this.busRepo.addOrUpdateBus(b);
    }

    @Override
    public Bus getBusById(int id) {
        return this.busRepo.getBusById(id);
    }

    @Override
    public boolean deleteBus(int id) {
        return this.busRepo.deleteBus(id);
    }

    @Override
    public boolean isExistBus(Bus b) {
        Map<String, String> params = new HashMap<>();
        params.put("plate", b.getPlate());
        List<Bus> busesCheck = this.busRepo.getBuses(params);
        if(busesCheck != null) 
            if(b.getId() != null && b.equals(busesCheck.get(0)))
                return false;
        return true;
    }
    
}
