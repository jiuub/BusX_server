package com.xaau.bs.busx.server.test;

import com.xaau.bs.busx.server.dao.UserDao;
import com.xaau.bs.busx.server.dao.impl.UserDaoImpl;
import com.xaau.bs.busx.server.domain.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDaoImplTest {

  @Test
  public void findAll() {
    UserDao userDao=new UserDaoImpl();
    List<User> users=userDao.findAll();
    for (int i=0;i<users.size();i++){
      User user=users.get(i);
      System.out.println(user.toString());
    }
  }

  @Test
  public void insertElement() {
    UserDao userDao=new UserDaoImpl();
    User user=new User();
    user.setUserName("555@555.cn");
    user.setPassword("666");
    userDao.insertElement(user);
  }
}