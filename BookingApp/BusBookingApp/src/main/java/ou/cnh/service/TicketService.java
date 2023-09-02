/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ou.cnh.service;

import java.util.List;
import java.util.Map;
import ou.cnh.pojo.Ticket;

/**
 *
 * @author zedmo
 */
public interface TicketService {
    List<Ticket> getTickets(Map<String, String> params);
    Ticket getTicketById(int id);
    boolean addOrUpdateTicket(Ticket t);
}
