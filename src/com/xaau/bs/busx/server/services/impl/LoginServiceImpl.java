package com.xaau.bs.busx.server.services.impl;

import com.xaau.bs.busx.server.dao.UserDao;
import com.xaau.bs.busx.server.dao.impl.UserDaoImpl;
import com.xaau.bs.busx.server.domain.User;
import com.xaau.bs.busx.server.services.LoginService;

import java.util.List;

public class LoginServiceImpl implements LoginService {
  UserDao userDao=new UserDaoImpl();
  @Override
  public String checkLogin(User user) {
    List<User> list = userDao.findAll();
    for(int i=0;i<list.size();i++) {
      User users=list.get(i);
      if(users.getUserName().equals(user.getUserName()) && users.getPassword().equals(user.getPassword())) {
        return "AccessConfirmed";
      }
    }
    return "AccessDenied";
  }

  @Override
  public String signUp(User user) {
    List<User> list = userDao.findAll();
    for(int i=0;i<list.size();i++) {
      User users=list.get(i);
      if(users.getUserName().equals(user.getUserName())) {
        return "user_already_exists";
      }
    }
    userDao.insertElement(user);
    return "sign_up_success";
  }

  @Override
  public String changePassword(String email, String oPassword, String nPassword) {
    List<User> list = userDao.findAll();
    for(int i=0;i<list.size();i++) {
      User users=list.get(i);
      if(users.getUserName().equals(email)&&users.getPassword().equals(oPassword)) {
        userDao.updateElement(email,nPassword);
        return "change_password_success";
      }
  }
    return "change_password_failed";
  }
}
