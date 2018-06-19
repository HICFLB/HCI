package edu.nju.onlineTicket.service.Impl;

import edu.nju.onlineTicket.dao.OrderDao;
import edu.nju.onlineTicket.dao.TicketDao;
import edu.nju.onlineTicket.model.Order;
import edu.nju.onlineTicket.model.Ticket;
import edu.nju.onlineTicket.service.OrderService;
import edu.nju.onlineTicket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by asus1 on 2018/3/2.
 */
@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketDao ticketDao;
    @Autowired
    private OrderDao orderDao;
    @Override
    public void createTickets(int performanceID, int[] seatNum, String[] seatType, String[] seatPrice) {
        //生成座位序列
        List<Ticket> tickets = new ArrayList<Ticket>();
        int init = 0;
        DecimalFormat df1=new DecimalFormat("0000");
        DecimalFormat df2=new DecimalFormat("00000");
        for(int i=0;i<seatNum.length;i++) {
            for(int j=0;j<seatNum[i];j++){
                String ticketID=df1.format(performanceID);
                Ticket ticket = new Ticket();
                ticket.setIsChecked(0);
                ticket.setPerformanceID(performanceID);
                ticket.setSeatType(seatType[i]);
                ticket.setNoOfSeats(j + init+1);
                ticketID = ticketID + df2.format(j+init+1);
                ticket.setTicketID(ticketID);
                ticket.setIsOccupied(0);
                ticket.setSingleCost(Double.parseDouble(seatPrice[i]));
                tickets.add(ticket);
            }
            init = init+seatNum[i];
        }
        ticketDao.setTickets(tickets);
    }

    @Override
    public void freeTickets(String[] ticketNum) {
        for(int i=0;i<ticketNum.length;i++){
            ticketDao.freeTickets(ticketNum[i]);
        }
    }

    @Override
    public void occupySeats(String ticketNo) {
        String[] tickets = ticketNo.split(";");
        for(int i=0;i<tickets.length;i++){
            ticketDao.occupySeats(tickets[i]);
        }
    }

    @Override
    public String checkTicket(String ticketID) {
        String message="";
        Ticket ticket = ticketDao.findTicket(ticketID);
        if(ticket!=null){
            if(ticket.getIsChecked()==1) {
                message = "AlreadyChecked";
            } else {
                ticket.setIsChecked(1);
                ticketDao.updateTicket(ticket);
                message = "Success";
            }
        }else{
            message = "NotExits";
        }
        return message;
    }

    @Override
    public long getCheckedNum(String performanceID) {
        return ticketDao.getCheckedNum(performanceID);
    }

    @Override
    public List<Ticket> getTicketsByPerformance(int performanceID) {
        List<Ticket> tickets = ticketDao.findTicketsByPerformance(performanceID);
        return tickets;
    }

    @Override
    public boolean OccupiedTickets(String situation, Order order) {
        int performanceID = order.getPerformanceID();
        String ticketNo = "";
        String[] buyInfo = situation.split(";");
        System.out.println("&&&&&&&&&&"+buyInfo.length);
        for(int i=0;i<buyInfo.length;i++){
            String buyType = buyInfo[i].split("\\s+")[0];
            String str = buyInfo[i].split("\\s+")[1];
            String str2="";
            if(str != null && !"".equals(str)){
                for(int j=0;j<str.length();j++){
                    if(str.charAt(j)>=48 && str.charAt(j)<=57){
                        str2+=str.charAt(j);
                    }
                }

            }
            int buyNum = Integer.valueOf(str2);
            List<Ticket> tickets = ticketDao.findFreeTickets(performanceID,buyType);
            if(tickets.size()>=buyNum){
                for(int p=0;p<buyNum;p++){
                    //把票站上 然后得到ticketNo
                    Ticket ticket = tickets.get(p);
                    ticket.setIsOccupied(1);
                    ticketDao.updateTicket(ticket);
                    ticketNo = ticketNo + ";";
                }
            }else{
                return false;
            }
        }
        order.setTicketNo(ticketNo);
        orderDao.update(order);
        return true;
    }
}
