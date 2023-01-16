package lk.ijse.salongeetha.to;

public class UserDTO {
   private String userName;
    private String eid;
    private String name;
    private String password;
    private String email;

    public UserDTO(String userName, String eid, String password) {
        this.userName = userName;
        this.eid = eid;
        this.password = password;
    }

    public UserDTO(){

    }

    public UserDTO(String userName, String eid, String name, String password, String email) {
        this.userName = userName;
        this.eid = eid;
        this.name = name;
        this.password = password;
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", nic='" + eid + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
