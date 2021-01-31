package coco.Models;

public class User {
  private String password;
  private String userName;
  private String checkPassword;


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }


  public String getCheckPassword() {
    return checkPassword;
  }

  public void setCheckPassword(String checkPassword) {
    this.checkPassword = checkPassword;
  }


  public void setPassword(String password) {
    this.password = password;
  }


}