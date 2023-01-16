package lk.ijse.salongeetha.entity;

public class User {
    private String userName;
    private String eid;
    private String password;

    public User() {
    }

    public User(String userName, String eid, String password) {
        this.userName = userName;
        this.eid = eid;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", eid='" + eid + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
