package lk.ijse.salongeetha.view.tm;

import com.jfoenix.controls.JFXButton;

public class BookTM {
    private String bokId;
    private String nic;
    private String rid;
    private String date;
    private int howManyDays;
    private int qty;
    private double price;
    private double discount;
    private double total;
    private JFXButton deleteButton;

    public BookTM() {
    }

    public BookTM(String bokId, String nic, String rid, String date, int howManyDays, int qty, double price, double discount, double total, JFXButton deleteButton) {
        this.bokId = bokId;
        this.nic = nic;
        this.rid = rid;
        this.date = date;
        this.howManyDays = howManyDays;
        this.qty = qty;
        this.price = price;
        this.discount = discount;
        this.total = total;
        this.deleteButton = deleteButton;
    }

    public String getBokId() {
        return bokId;
    }

    public void setBokId(String bokId) {
        this.bokId = bokId;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHowManyDays() {
        return howManyDays;
    }

    public void setHowManyDays(int howManyDays) {
        this.howManyDays = howManyDays;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public JFXButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JFXButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
