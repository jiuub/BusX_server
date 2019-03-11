package com.xaau.bs.busx.server.domain;

public class User {
  private int userID;
  private String userName;
  private String password;

  public User() {
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{" +
            "userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
