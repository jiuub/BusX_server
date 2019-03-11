package com.xaau.bs.busx.server.test;

import com.xaau.bs.busx.server.util.JdbcUtils;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JdbcUtilsTest {

  @org.junit.Test
  public void getConnection() {
  }

  @org.junit.Test
  public void releaseConn() {
  }

  @org.junit.Test
  public void findSimpleResult() {
    JdbcUtils jdbcUtils=new JdbcUtils();
    jdbcUtils.getConnection();
    String sql= "select * from bus where busname = ? and busid = ? ";
    List<Object> params=new ArrayList<Object>();
    params.add("611路");
    params.add(1);
    try {
      Map<String,Object> map=jdbcUtils.findSimpleResult(sql,params);
      System.out.println(map);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.releaseConn();
    }
  }

  @org.junit.Test
  public void findMoreResult() {
    JdbcUtils jdbcUtils=new JdbcUtils();
    jdbcUtils.getConnection();
    String sql="select sta_Name from line_view where busname = ? and direction = ? ";
    List<Object> params=new ArrayList<Object>();
    params.add("611路");
    params.add("go");
    try {
      List<Map<String,Object>> list=jdbcUtils.findMoreResult(sql,params);
      System.out.println(list);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.releaseConn();
    }
  }

  @Test
  public void insertByPreparedStatement(){
    JdbcUtils jdbcUtils=new JdbcUtils();
    jdbcUtils.getConnection();
    String sql="INSERT INTO user(userName,password) VALUES(?,?);";
    List<Object> params=new ArrayList<Object>();
    params.add("666@666.com");
    params.add("6666666");
    try {
      boolean b=jdbcUtils.updateByPreparedStatement(sql,params);
      System.out.println(b);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.releaseConn();
    }
  }

  @Test
  public void deleteByPreparedStatement(){
    JdbcUtils jdbcUtils=new JdbcUtils();
    jdbcUtils.getConnection();
    String sql="delete from user where username = ? ";
    List<Object> params=new ArrayList<Object>();
    params.add("666@666.com");
    try {
      boolean b=jdbcUtils.updateByPreparedStatement(sql,params);
      System.out.println(b);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.releaseConn();
    }
  }

  @Test
  public void updateByPreparedStatement(){
    JdbcUtils jdbcUtils=new JdbcUtils();
    jdbcUtils.getConnection();
    String sql="update user set password = ? where username = ? ";
    List<Object> params=new ArrayList<Object>();
    params.add("6666666");
    params.add("666@666.com");
    try {
      boolean b=jdbcUtils.updateByPreparedStatement(sql,params);
      System.out.println(b);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.releaseConn();
    }
  }
}