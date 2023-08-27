/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.cnh.pojo.BusType;
import ou.cnh.repository.BusTypeRepository;
import ou.cnh.service.BusTypeService;

/**
 *
 * @author zedmo
 */
@Service
public class BusTypeServiceImpl implements BusTypeService{
    
    @Autowired
    private BusTypeRepository busTypeRepo;

    @Override
    public List<BusType> getBusTypes(Map<String, String> params) {
        return this.busTypeRepo.getBusTypes(params);
    }

    @Override
    public boolean addOrUpdateBusType(BusType bt) {
        return this.busTypeRepo.addOrUpdateBusType(bt);
    }

    @Override
    public BusType getBusTypeById(int id) {
        return this.busTypeRepo.getBusTypeById(id);
    }

    @Override
    public boolean deleteBusType(int id) {
        return this.busTypeRepo.deleteBusType(id);
    }
    
}
