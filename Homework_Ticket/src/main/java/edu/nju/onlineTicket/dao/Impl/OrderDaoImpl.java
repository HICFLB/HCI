package edu.nju.onlineTicket.dao.Impl;

import edu.nju.onlineTicket.dao.BaseDao;
import edu.nju.onlineTicket.dao.OrderDao;
import edu.nju.onlineTicket.model.Order;
import edu.nju.onlineTicket.model.Performance;
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
public class OrderDaoImpl implements OrderDao {
    @Autowired
    BaseDao baseDao;


    @Override
    public int save(Order order) {
        return baseDao.save(order);
    }

    @Override
    public void update(Order order) {
        baseDao.update(order);
    }

    @Override
    public Order load(int orderID) {
        Session session = baseDao.getNewSession();
        Order order = session.get(Order.class,orderID);
        session.close();
        return order;
    }

    @Override
    public List findPersonalOrderByType(int type, String email) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        List<Order> result = new ArrayList();
        try {
            String hql="from Order where type =:theType and email=:email";
            Query query=session.createQuery(hql);
            query.setParameter("theType",type);
            query.setParameter("email",email);
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

    @Override
    public List findOrderByType(int type) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        List<Order> result = new ArrayList();
        try {
            String hql="from Order where type =:theType";
            Query query=session.createQuery(hql);
            query.setParameter("theType",type);
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

    @Override
    public List findUnOccupiedOrder() {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        List<Order> result = new ArrayList();
        try {
            String hql="from Order where type =:theType and ticketNo=:ticketNo";
            Query query=session.createQuery(hql);
            query.setParameter("theType",1);
            query.setParameter("ticketNo","暂未配票");
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

    @Override
    public Order findPersonalOrderByPerform(String email, int performanceID, double orderPrice) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        Order order = new Order();
        try {
            String hql="from Order where performanceID =:performID and email=:email and price=:orderPrice";
            Query<Order> query=session.createQuery(hql);
            query.setParameter("performID",performanceID);
            query.setParameter("email",email);
            query.setParameter("orderPrice",orderPrice);
            order=query.getResultList().get(0);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
        return order;
    }

    @Override
    public void cancelOrder(Order order) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        order.setType(4);
        session.update(order);
        tx.commit();
    }

    @Override
    public boolean updateType(int orderID, int type) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        boolean flag = false;
        try {
            String hql="update Order set type=:theType where orderID =:orderID";
            Query query=session.createQuery(hql);
            query.setParameter("theType",type);
            query.setParameter("orderID",orderID);
            query.executeUpdate();
            tx.commit();
            flag = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
        return flag;
    }

    @Override
    public List findOrderByPerform(int performanceID) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        List<Order> result = new ArrayList();
        try {
            String hql="from Order where performanceID =:performID";
            Query query=session.createQuery(hql);
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

    @Override
    public List findAll() {
        return baseDao.getAllList(Order.class);
    }

/*    @Override
    public long getBookNum(int performanceID) {
        Session session = baseDao.getNewSession();
        String hql = "select count(*) from Order where performanceID =:performID and type=0 or type=1 or type=2 or type=3";
        Long count = (Long) session.createQuery(hql).setParameter("performID",performanceID).uniqueResult();
        session.close();
        System.out.println("@@@@@@@@@@预订的"+count);
        return count != null ? count.longValue() : 0;
    }

    @Override
    public long getUnsubscribeNum(int performanceID) {
        Session session = baseDao.getNewSession();
        String hql = "select count(*) from Order where performanceID =:performID and type=4";
        Long count = (Long) session.createQuery(hql).setParameter("performID",performanceID).uniqueResult();
        session.close();
        System.out.println("@@@@@@@@@@退订的"+count);
        return count != null ? count.longValue() : 0;
    }*/
}
