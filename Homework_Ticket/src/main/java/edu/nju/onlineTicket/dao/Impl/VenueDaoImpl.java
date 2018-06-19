package edu.nju.onlineTicket.dao.Impl;

import edu.nju.onlineTicket.dao.BaseDao;
import edu.nju.onlineTicket.dao.VenueDao;
import edu.nju.onlineTicket.model.SubVenue;
import edu.nju.onlineTicket.model.Venue;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus1 on 2018/3/1.
 */
@Repository
public class VenueDaoImpl implements VenueDao {
    @Autowired
    private BaseDao baseDao;

    @Override
    public int save(Venue venue) {
        return baseDao.save(venue);
    }

    @Override
    public void update(Venue venue) {
        baseDao.update(venue);
    }

    @Override
    public Venue findVenue(String venueName) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        Venue venue = new Venue();
        try {
            String hql="from Venue where venueName =:name";
            Query<Venue> query=session.createQuery(hql);
            query.setParameter("name",venueName);
            if(!query.list().isEmpty())
                venue = query.getResultList().get(0);
            else
                venue = null;
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
        return venue;
    }

    @Override
    public Venue load(String venueID) {
        Session session = baseDao.getNewSession();
        Venue venue = session.get(Venue.class, Integer.valueOf(venueID));
        session.close();
        return venue;
    }

    @Override
    public SubVenue findSubVenue(String subVenueID) {
        Session session = baseDao.getNewSession();
        SubVenue subVenue = session.get(SubVenue.class, Integer.valueOf(subVenueID));
        session.close();
        return subVenue;
    }

    @Override
    public List findAll() {
        return baseDao.getAllList(Venue.class);
    }

    @Override
    public List findAllSubVenue() {
        return baseDao.getAllList(SubVenue.class);
    }

    @Override
    public List findNotChecked() {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        List result = new ArrayList();
        try {
            String hql="from Venue where isChecked =:isCheck";
            Query<Venue> query=session.createQuery(hql);
            query.setParameter("isCheck",0);
            result = query.list();
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
    public List findSubNotChecked() {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        List result = new ArrayList();
        try {
            String hql="from SubVenue where isChecked =:isCheck";
            Query<SubVenue> query=session.createQuery(hql);
            query.setParameter("isCheck",0);
            result = query.list();
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
    public List findPassedList() {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        List result = new ArrayList();
        try {
            String hql="from Venue where isChecked =:isCheck and isPassed =:isPass";
            Query<Venue> query=session.createQuery(hql);
            query.setParameter("isCheck",1);
            query.setParameter("isPass",1);
            result = query.list();
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
    public List findSubPassedList() {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        List result = new ArrayList();
        try {
            String hql="from SubVenue where isChecked =:isCheck and isPassed =:isPass";
            Query<SubVenue> query=session.createQuery(hql);
            query.setParameter("isCheck",1);
            query.setParameter("isPass",1);
            result = query.list();
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
    public List findRefusedList() {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        List result = new ArrayList();
        try {
            String hql="from Venue where isChecked =:isCheck and isPassed =:isPass";
            Query<Venue> query=session.createQuery(hql);
            query.setParameter("isCheck",1);
            query.setParameter("isPass",0);
            result = query.list();
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
    public List findSubRefusedList() {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        List result = new ArrayList();
        try {
            String hql="from SubVenue where isChecked =:isCheck and isPassed =:isPass";
            Query<SubVenue> query=session.createQuery(hql);
            query.setParameter("isCheck",1);
            query.setParameter("isPass",0);
            result = query.list();
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
    public boolean updateVenue(String venueID, int state) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        boolean flag = false;
        try {
            String hql="update Venue set isChecked =:isCheck , isPassed =:state where idCode =:venueID";//使用命名参数，推荐使用，易读。
            Query query=session.createQuery(hql);
            query.setParameter("venueID",Integer.parseInt(venueID));
            query.setParameter("isCheck",1);
            query.setParameter("state",state);
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
    public boolean updateSubVenue(String subVenueID, int state) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        boolean flag = false;
        try {
            String hql="update SubVenue set isChecked =:isCheck , isPassed =:state where subVenueID =:subVenueID";//使用命名参数，推荐使用，易读。
            Query query=session.createQuery(hql);
            query.setParameter("subVenueID",Integer.parseInt(subVenueID));
            query.setParameter("isCheck",1);
            query.setParameter("state",state);
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
    public boolean modifyPassword(String venueID, String password) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        boolean flag = false;
        try {
            String hql="update Venue set password=:pwd where idCode =:venueID";
            Query query=session.createQuery(hql);
            query.setParameter("venueID",Integer.parseInt(venueID));
            query.setParameter("pwd",password);
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
    public boolean addSubVenue(SubVenue subVenue) {
        return baseDao.save(subVenue)>0;
    }

    @Override
    public boolean updateModifyState(String venueID, int state) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        boolean flag = false;
        try {
            String hql="update Venue set modifyState =:state where idCode =:venueID";//使用命名参数，推荐使用，易读。
            Query query=session.createQuery(hql);
            query.setParameter("venueID",Integer.parseInt(venueID));
            query.setParameter("state",state);
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
}
