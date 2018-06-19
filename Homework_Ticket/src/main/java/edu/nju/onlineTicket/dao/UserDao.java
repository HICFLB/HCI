package edu.nju.onlineTicket.dao;

import edu.nju.onlineTicket.model.User;

import java.util.List;

/**
 * Created by asus1 on 2018/2/27.
 */
public interface UserDao {
    /**
     * 添加用户
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 根据email查找用户
     * @param email
     * @return
     */
    User load(String email);

    /**
     * 根据激活码激活用户状态
     * @param code
     * @return
     */
    boolean updateState(String code);

    boolean cancelUser(String email);

    boolean modifyUserName(String email, String userName);

    boolean modifyPassword(String email, String password);

    void update(User user);

    List<User> findAll();
}
