package com.xaau.bs.busx.server.dao.impl;

import com.xaau.bs.busx.server.dao.UserDao;
import com.xaau.bs.busx.server.domain.User;
import com.xaau.bs.busx.server.util.JdbcUtils;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

  @Override
  public List<User> findAll() {
    List<User> userList=null;
    JdbcUtils jdbcUtils=new JdbcUtils();
    jdbcUtils.getConnection();
    String sql="select * from user where 1 = ? ";
    List<Object> params=new ArrayList<Object>();
    params.add(1);
    try {
      userList=jdbcUtils.findMoreBeanResult(sql,params,User.class);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.releaseConn();
    }
    return userList;
  }

  @Override
  public void insertElement(User user) {
    JdbcUtils jdbcUtils=new JdbcUtils();
    jdbcUtils.getConnection();
    String sql="insert into user(userName,password) values(?,?);";
    List<Object> params=new ArrayList<Object>();
    params.add(user.getUserName());
    params.add(user.getPassword());
    try {
      jdbcUtils.updateByPreparedStatement(sql,params);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.releaseConn();
    }
  }

  @Override
  public void updateElement(String email,String nPassword) {
    JdbcUtils jdbcUtils=new JdbcUtils();
    jdbcUtils.getConnection();
    String sql="update user set password = ? where username = ? ";
    List<Object> params=new ArrayList<Object>();
    params.add(nPassword);
    params.add(email);
    try {
      jdbcUtils.updateByPreparedStatement(sql,params);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.releaseConn();
    }
  }
}
