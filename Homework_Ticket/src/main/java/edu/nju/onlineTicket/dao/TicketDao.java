package edu.nju.onlineTicket.dao;

import edu.nju.onlineTicket.model.Ticket;

import java.util.List;

/**
 * Created by asus1 on 2018/3/1.
 */
public interface TicketDao {
    void setTickets(List<Ticket> tickets);
    void freeTickets(String ticketNum);
    void occupySeats(String ticketID);
    Ticket findTicket(String ticketID);
    void updateTicket(Ticket ticket);
    long getCheckedNum(String performanceID);
    List findFreeTickets(int performanceID, String seatType);
    List<Ticket> findTicketsByPerformance(int performanceID);
}
