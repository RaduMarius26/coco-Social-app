package coco.aplication;

class User {

  private String password, username;

  public User() {
    this.password = "password";
    this.username = "username";
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}