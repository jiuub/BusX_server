package com.xaau.bs.busx.server.action;

import com.xaau.bs.busx.server.domain.User;
import com.xaau.bs.busx.server.services.LoginService;
import com.xaau.bs.busx.server.services.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getContextPath();
    response.setContentType("text/html;charset=utf-8");
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String sign = request.getParameter("sign");
    PrintWriter out=response.getWriter();
    User user=new User();
    user.setUserName(username);
    user.setPassword(password);
    LoginService loginService=new LoginServiceImpl();
    if("in".equals(sign)) {
      String loginInfo=loginService.checkLogin(user);
      out.print(loginInfo);
    }else if("up".equals(sign)) {
      String registerInfo=loginService.signUp(user);
      out.print(registerInfo);
    } else if ("change".equals(sign)) {
      String npassword = request.getParameter("npassword");
      String changeInfo=loginService.changePassword(user.getUserName(),user.getPassword(),npassword);
      out.print(changeInfo);
    }
    System.out.println("username = " + username + " pswd = " + password);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
  }
}
