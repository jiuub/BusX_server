package com.xaau.bs.busx.server.dao;

import com.xaau.bs.busx.server.domain.User;

import java.util.List;

public interface UserDao {
  List<User> findAll();
  void insertElement(User people);
  void updateElement(String email,String nPassword);
}
