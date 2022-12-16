package lk.ijse.salongeetha.to.tm;

import com.jfoenix.controls.JFXButton;

public class CustomerTM {
    private String nic;
    private String name;
    private String phoneNumber;
    private String email;
    private String dob;
    private String userName;
    private JFXButton deleteButton;
    private JFXButton updateButton;
    private JFXButton mailButton;

    public CustomerTM() {
    }

    public CustomerTM(String nic, String name, String phoneNumber, String email, String dob, JFXButton deleteButton, JFXButton updateButton, JFXButton mailButton) {
        this.nic = nic;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dob = dob;
        this.deleteButton = deleteButton;
        this.updateButton = updateButton;
        this.mailButton = mailButton;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public JFXButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JFXButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JFXButton getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(JFXButton updateButton) {
        this.updateButton = updateButton;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "nic='" + nic + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", userName='" + userName + '\'' +
                ", deleteButton=" + deleteButton +
                ", updateButton=" + updateButton +
                '}';
    }

    public JFXButton getMailButton() {
        return mailButton;
    }

    public void setMailButton(JFXButton mailButton) {
        this.mailButton = mailButton;
    }
}
