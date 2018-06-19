package edu.nju.onlineTicket.dao.Impl;

import edu.nju.onlineTicket.dao.BaseDao;
import edu.nju.onlineTicket.dao.TicketDao;
import edu.nju.onlineTicket.model.Ticket;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus1 on 2018/3/1.
 */
@Repository
public class TicketDaoImpl implements TicketDao {
    @Autowired
    BaseDao baseDao;

    @Override
    public void setTickets(List<Ticket> tickets) {
        for(Ticket ticket : tickets) {
            baseDao.save(ticket);
        }
    }

    @Override
    public void freeTickets(String ticketNum) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        String hql="update Ticket set isOccupied=:occupied where ticketID =:ticketNum";
        Query query=session.createQuery(hql);
        query.setParameter("occupied",0);
        query.setParameter("ticketNum",ticketNum);
        query.executeUpdate();
        tx.commit();
        tx.rollback();
        session.close();
    }

    @Override
    public void occupySeats(String ticketID) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        String hql="update Ticket set isOccupied=:occupied where ticketID =:ticketID";
        Query query=session.createQuery(hql);
        query.setParameter("occupied",1);
        query.setParameter("ticketID",ticketID);
        query.executeUpdate();
        tx.commit();
        tx.rollback();
        session.close();
    }

    @Override
    public Ticket findTicket(String ticketID) {
        return (Ticket) baseDao.load(Ticket.class,ticketID);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        baseDao.update(ticket);
    }

    @Override
    public long getCheckedNum(String performanceID) {
        Session session = baseDao.getNewSession();
        String hql = "select count(*) from Ticket where performanceID =:performID and isChecked=:isChecked";
        Long count = (Long) session.createQuery(hql).setParameter("performID",Integer.parseInt(performanceID)).setParameter("isChecked",1).uniqueResult();
        session.close();
        return count != null ? count.longValue() : 0;
    }

    @Override
    public List findFreeTickets(int performanceID, String seatType) {
        Session session = baseDao.getNewSession();
        String hql = "from Ticket where performanceID =:performID and isOccupied=:isOccupied and seatType=:seatType";
        List<Ticket> tickets = session.createQuery(hql).setParameter("performID",performanceID).setParameter("isOccupied",0).setParameter("seatType",seatType).getResultList();
        session.close();
        return tickets;
    }

    @Override
    public List<Ticket> findTicketsByPerformance(int performanceID) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        List<Ticket> result = new ArrayList();
        try {
            String hql="from Ticket where performanceID =:performID";
            Query<Ticket> query=session.createQuery(hql);
            query.setParameter("performID",performanceID);
            result=query.getResultList();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
        return result;
    }
}
