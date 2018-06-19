package edu.nju.onlineTicket.dao.Impl;

import edu.nju.onlineTicket.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus1 on 2018/2/27.
 */
@Repository
public class BaseDaoImpl implements BaseDao {
    /** * Autowired 自动装配 相当于get() set() */
    @Autowired
    protected SessionFactory sessionFactory;

    /** * gerCurrentSession 会自动关闭session，使用的是当前的session事务 * * @return */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /** * openSession 需要手动关闭session 意思是打开一个新的session * * @return */
    public Session getNewSession() {
        return sessionFactory.openSession();
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }

    /** * 根据 id 查询信息 * * @param id * @return */
    @SuppressWarnings("rawtypes")
    public Object load(Class c, String id) {
        Session session = getNewSession();
        Object result = session.get(c, id);
        session.close();
        return result;
    }

    /** * 获取所有信息 * * @param c * * @return */

    public List getAllList(Class c) {
        String hql = "from " + c.getName();
        Session session = getNewSession();
        List list = session.createQuery(hql).list();
        session.close();
        return list;

    }

    /** * 获取总数量 * * @param c * @return */

    public Long getTotalCount(Class c) {
        Session session = getNewSession();
        String hql = "select count(*) from " + c.getName();
        Long count = (Long) session.createQuery(hql).uniqueResult();
        session.close();
        return count != null ? count.longValue() : 0;
    }

    /** * 保存 * * @param bean * */
    public int save(Object bean) {
        Session session = getNewSession();
        Transaction tx = session.beginTransaction();
        int flag = 0;
        try{
            session.save(bean);
            tx.commit();
            flag = 1;
        }catch(Exception ex){
            tx.rollback();
        }finally {
            session.close();
        }
        return flag;
    }

    /** * 更新 * * @param bean * */
    public void update(Object bean) {
        Session session = getNewSession();
        Transaction tx = session.beginTransaction();
        session.update(bean);
        tx.commit();
        session.close();
    }

    /** * 删除 * * @param bean * */
    public void delete(Object bean) {

        Session session = getNewSession();
        session.delete(bean);
        session.flush();
        session.clear();
        session.close();
    }

    /** * 根据ID删除 * * @param c 类 * * @param id ID * */
    @SuppressWarnings({ "rawtypes" })
    public void delete(Class c, String id) {
        Session session = getNewSession();
        Object obj = session.get(c, id);
        session.delete(obj);
        flush();
        clear();
    }

    /** * 批量删除 * * @param c 类 * * @param ids ID 集合 * */
    @SuppressWarnings({ "rawtypes" })
    public void delete(Class c, String[] ids) {
        for (String id : ids) {
            Object obj = getSession().get(c, id);
            if (obj != null) {
                getSession().delete(obj);
            }
        }
    }
}
