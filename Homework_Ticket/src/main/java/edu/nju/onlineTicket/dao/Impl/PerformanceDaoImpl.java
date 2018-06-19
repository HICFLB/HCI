package edu.nju.onlineTicket.dao.Impl;

import edu.nju.onlineTicket.dao.BaseDao;
import edu.nju.onlineTicket.dao.PerformanceDao;
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
public class PerformanceDaoImpl implements PerformanceDao {
    @Autowired
    BaseDao baseDao;
    @Override
    public int save(Performance performance) {
        return baseDao.save(performance);
    }

    @Override
    public int getPerformanceID(String venueID, String performanceName) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        int performanceID=0;
        try {
            String hql="from Performance where venueID =:venueID and performanceName=:performName";
            Query<Performance> query=session.createQuery(hql);
            query.setParameter("venueID",Integer.parseInt(venueID));
            query.setParameter("performName",performanceName);
            List<Performance> list = query.getResultList();
            if(list.size()>0)
                performanceID = list.get(0).getPerformanceID();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
        return performanceID;
    }

    @Override
    public List<Performance> findAll() {
        return baseDao.getAllList(Performance.class);
    }

    @Override
    public List<Performance> findPerformanceByType(int type) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        List<Performance> result = new ArrayList();
        try {
            String hql="from Performance where performanceType = ?";
            result=session.createQuery(hql).setParameter(0,type).list();
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
    public List<Performance> findPerformanceByVenue(int venueID) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        List<Performance> result = new ArrayList();
        try {
            String hql="from Performance where venueID =:venueID";
            Query<Performance> query=session.createQuery(hql);
            query.setParameter("venueID",venueID);
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
    public List<Performance> findPerformanceByTypeVenue(int type, int venueID) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        List<Performance> result = new ArrayList();
        try {
            String hql="from Performance where venueID =:venueID and performanceType=:performType";
            Query<Performance> query=session.createQuery(hql);
            query.setParameter("venueID",venueID);
            query.setParameter("performType",type);
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
    public Performance find(int performanceID) {
        Session session = baseDao.getNewSession();
        Performance performance = session.get(Performance.class, performanceID);
        session.close();
        return performance;
    }

    @Override
    public void update(Performance performance) {
        baseDao.update(performance);
    }
}
