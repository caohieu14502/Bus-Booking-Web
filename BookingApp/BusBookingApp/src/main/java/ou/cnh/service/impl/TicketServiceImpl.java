/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ou.cnh.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.cnh.pojo.Ticket;
import ou.cnh.repository.TicketRepository;
import ou.cnh.service.TicketService;

/**
 *
 * @author zedmo
 */
@Service
public class TicketServiceImpl implements TicketService{
    
    @Autowired
    private TicketRepository ticketRepo;

    @Override
    public List<Ticket> getTickets(Map<String, String> params) {
        return this.ticketRepo.getTickets(params);
    }

    @Override
    public Ticket getTicketById(int id) {
        return this.ticketRepo.getTicketById(id);
    }

    @Override
    public boolean addOrUpdateTicket(Ticket t) {
        return this.ticketRepo.addOrUpdateTicket(t);
    }
    
}
