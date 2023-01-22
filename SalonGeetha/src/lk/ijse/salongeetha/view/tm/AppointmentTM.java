package lk.ijse.salongeetha.view.tm;

import com.jfoenix.controls.JFXButton;

public class AppointmentTM {
    private String aptId;
    private String empId;
    private String sevId;
    private String nic;
    private String date;
    private String time;
    private double discount;
    private double price;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count;
    private JFXButton deleteButton;


    public AppointmentTM(String aptId, String empId, String sevId, String nic, String date, String time, double discount, double price, JFXButton deleteButton) {
        this.aptId = aptId;
        this.empId = empId;
        this.sevId = sevId;
        this.nic = nic;
        this.date = date;
        this.time = time;
        this.discount = discount;
        this.price = price;
        this.deleteButton = deleteButton;
    }

    public AppointmentTM() {

    }

    public String getAptId() {
        return aptId;
    }

    public void setAptId(String aptId) {
        this.aptId = aptId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getSevId() {
        return sevId;
    }

    public void setSevId(String sevId) {
        this.sevId = sevId;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public JFXButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JFXButton deleteButton) {
        this.deleteButton = deleteButton;
    }
}
