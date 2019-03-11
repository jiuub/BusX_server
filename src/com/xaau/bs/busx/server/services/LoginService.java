package com.xaau.bs.busx.server.services;

import com.xaau.bs.busx.server.domain.User;

public interface LoginService {
  String checkLogin(User user);
  String signUp(User user);
  String changePassword(String email, String oPassword, String nPassword);
}
