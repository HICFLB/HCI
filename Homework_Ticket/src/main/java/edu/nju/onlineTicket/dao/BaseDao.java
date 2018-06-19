package edu.nju.onlineTicket.dao;

import org.hibernate.Session;

import java.util.List;

/**
 * Created by asus1 on 2018/2/27.
 */
public interface BaseDao {

    Session getSession();

    Session getNewSession();

    void flush();

    void clear() ;

    Object load(Class c, String id) ;


    List getAllList(Class c) ;

    Long getTotalCount(Class c) ;

    int save(Object bean) ;

    void update(Object bean) ;

    void delete(Object bean) ;

    void delete(Class c, String id) ;

    void delete(Class c, String[] ids) ;
}
