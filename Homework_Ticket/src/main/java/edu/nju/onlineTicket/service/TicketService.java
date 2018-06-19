package edu.nju.onlineTicket.service;

import edu.nju.onlineTicket.model.Order;
import edu.nju.onlineTicket.model.Ticket;

import java.util.List;

/**
 * Created by asus1 on 2018/3/2.
 */
public interface TicketService {
    void createTickets(int performanceID, int[] seatNum, String[] seatType, String[] seatPrice);
    void freeTickets(String[] ticketNum);
    void occupySeats(String ticketNo);
    String checkTicket(String ticketID);
    long getCheckedNum(String performanceID);
    List<Ticket> getTicketsByPerformance(int performanceID);
    boolean OccupiedTickets(String situation,Order order);
}
