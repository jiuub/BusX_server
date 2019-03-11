package com.xaau.bs.busx.server.services;

import com.xaau.bs.busx.server.util.JdbcUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonService {

  /**
   * 获取线路的所有站点
   * @param busname 线路名
   * @param direction 方向
   * @return
   */
  public List<Map<String,Object>> getListBus(String busname,String direction){
    List<Map<String,Object>> list=null;
    JdbcUtils jdbcUtils=new JdbcUtils();
    jdbcUtils.getConnection();
    String sql="select sta_No , sta_Name from line_view where busname = ? and direction = ? ";
    List<Object> params=new ArrayList<Object>();
    params.add(busname);
    params.add(direction);
    try {
      list=jdbcUtils.findMoreResult(sql,params);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.releaseConn();
    }
    return list;
  }

  /**
   * 获取站点的所有公交
   * @param sta_Name 站点名
   * @return
   */
  public List<Map<String,Object>> getListStation(String sta_Name){
    List<Map<String,Object>> list=null;
    JdbcUtils jdbcUtils=new JdbcUtils();
    jdbcUtils.getConnection();
    String sql="select distinct busName from line_view where sta_Name = ? ";
    List<Object> params=new ArrayList<Object>();
    params.add(sta_Name);
    try {
      list=jdbcUtils.findMoreResult(sql,params);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.releaseConn();
    }
    return list;
  }

  /**
   * 获取公交详细信息
   * @param busname
   * @return
   */
  public Map<String,Object> getSimpleBus(String busname){
    Map<String,Object> map=null;
    JdbcUtils jdbcUtils=new JdbcUtils();
    jdbcUtils.getConnection();
    String sql= "select * from bus where busName = ? ";
    List<Object> params=new ArrayList<Object>();
    params.add(busname);
    try {
      map=jdbcUtils.findSimpleResult(sql,params);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.releaseConn();
    }
    return map;
  }
}
