package com.xaau.bs.busx.server.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class JdbcUtils {
  private static String driver;
  private static String url;
  private static String username;
  private static String password;

  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet resultSet;

  static {
    Properties properties = new Properties();
    try {
      InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
      properties.load(inputStream);
      driver = properties.getProperty("driver");
      url = properties.getProperty("url");
      username = properties.getProperty("username");
      password = properties.getProperty("password");
      Class.forName(driver);
      System.out.println("注册驱动成功");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 获取数据库连接
   *
   * @return
   */
  public Connection getConnection() {
    conn = null;
    try {
      conn = DriverManager.getConnection(url, username, password);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return conn;
  }

  /**
   * 释放连接
   *
   */
  public void releaseConn() {
    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (pstmt != null) {
      try {
        pstmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 查询单条记录
   *
   * @param sql
   * @param params
   * @return
   */
  public Map<String, Object> findSimpleResult(String sql, List params) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    int index = 1;
    pstmt = conn.prepareStatement(sql);
    if (params != null && !params.isEmpty()) {
      for (int i = 0; i < params.size(); i++) {
        pstmt.setObject(index++, params.get(i));
      }
    }
    resultSet = pstmt.executeQuery();
    ResultSetMetaData metaData = resultSet.getMetaData();
    int col_len = metaData.getColumnCount();
    while (resultSet.next()) {
      for (int i = 0; i < col_len; i++) {
        String col_name = metaData.getColumnName(i + 1);
        Object col_value = resultSet.getObject(col_name);
        if (col_value == null) {
          col_value = "";
        }
        map.put(col_name, col_value);
      }
    }
    return map;
  }

  /**
   * 查询多条记录
   *
   * @param sql
   * @param params
   * @return
   * @throws SQLException
   */
  public List<Map<String, Object>> findMoreResult(String sql, List<Object> params) throws SQLException {
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    int index = 1;
    pstmt = conn.prepareStatement(sql);
    if (params != null && !params.isEmpty()) {
      for (int i = 0; i < params.size(); i++) {
        pstmt.setObject(index++, params.get(i));
      }
    }
    resultSet = pstmt.executeQuery();
    ResultSetMetaData metaData = resultSet.getMetaData();
    int cols_len = metaData.getColumnCount();
    while (resultSet.next()) {
      Map<String, Object> map = new HashMap<String, Object>();
      for (int i = 0; i < cols_len; i++) {
        String cols_name = metaData.getColumnName(i + 1);
        Object cols_value = resultSet.getObject(cols_name);
        if (cols_value==null){
          cols_value="";
        }
        map.put(cols_name,cols_value);
      }
      list.add(map);
    }
    return list;
  }

  /**
   * 查询多条记录List<bean>
   * @param sql
   * @param params
   * @param cls
   * @param <T>
   * @return
   * @throws Exception
   */
  public <T> List<T> findMoreBeanResult(String sql, List<Object> params, Class<T> cls) throws Exception {
    List<T> list = new ArrayList<T>();
    int index = 1;
    pstmt = conn.prepareStatement(sql);
    if (params != null && !params.isEmpty()) {
      for (int i = 0; i < params.size(); i++) {
        pstmt.setObject(index++, params.get(i));
      }
    }
    resultSet = pstmt.executeQuery();
    ResultSetMetaData metaData = resultSet.getMetaData();
    int cols_len = metaData.getColumnCount();
    while (resultSet.next()) {
      T resultObject = cls.newInstance();
      for (int i = 0; i < cols_len; i++) {
        String cols_name = metaData.getColumnName(i + 1);
        Object cols_value = resultSet.getObject(cols_name);
        if (cols_value == null) {
          cols_value = "";
         }
        Field field = cls.getDeclaredField(cols_name);
        field.setAccessible(true);
        field.set(resultObject, cols_value);
      }
      list.add(resultObject);
    }
    return list;
  }

  /**
   * 添加删除和修改的操作
   * @param sql
   * @param params
   * @return
   * @throws SQLException
   */
  public boolean updateByPreparedStatement(String sql, List<Object> params) {
    // 表示当用户执行添加删除和修改的时候所影响数据库的行数
    int result = -1;
    try {
      pstmt = conn.prepareStatement(sql);
      int index = 1;
      if (params != null && !params.isEmpty()) {
        for (int i = 0; i < params.size(); i++) {
          pstmt.setObject(index++, params.get(i));
        }
      }
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      return false;
    }
    return result > 0;
  }
}
