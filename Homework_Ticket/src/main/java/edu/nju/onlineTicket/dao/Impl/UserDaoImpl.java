package edu.nju.onlineTicket.dao.Impl;

import edu.nju.onlineTicket.dao.BaseDao;
import edu.nju.onlineTicket.dao.UserDao;
import edu.nju.onlineTicket.model.User;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by asus1 on 2018/2/28.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private BaseDao baseDao;

    @Override
    public int save(User user) {
        return baseDao.save(user);
    }

    @Override
    public User load(String email) {
        return (User)baseDao.load(User.class ,email);
    }

    @Override
    public boolean updateState(String code) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        boolean flag = false;
        try {
            String hql="update User set state=:stateInt where code =:idCode";//使用命名参数，推荐使用，易读。
            Query query=session.createQuery(hql);
            query.setParameter("idCode",code);
            query.setParameter("stateInt",1);
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
    public boolean cancelUser(String email) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        boolean flag = false;
        try {
            String hql="update User set isLogOff=:state where email =:email";
            Query query=session.createQuery(hql);
            query.setParameter("email",email);
            query.setParameter("state",1);
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
    public boolean modifyUserName(String email, String userName) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        boolean flag = false;
        try {
            String hql="update User set username=:name where email =:email";
            Query query=session.createQuery(hql);
            query.setParameter("email",email);
            query.setParameter("name",userName);
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
    public boolean modifyPassword(String email, String password) {
        Session session = baseDao.getNewSession();
        Transaction tx = session.beginTransaction();
        boolean flag = false;
        try {
            String hql="update User set password=:pwd where email =:email";
            Query query=session.createQuery(hql);
            query.setParameter("email",email);
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
    public void update(User user) {
        baseDao.update(user);
    }

    @Override
    public List<User> findAll() {
        return baseDao.getAllList(User.class);
    }


}
