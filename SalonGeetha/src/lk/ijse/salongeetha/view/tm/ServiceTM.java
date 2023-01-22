package lk.ijse.salongeetha.view.tm;

import com.jfoenix.controls.JFXButton;

public class ServiceTM {
    private String sevId;
    private String description;
    private String name;
    private double price;
    private double discount;
    private JFXButton deleteButton;
    private JFXButton updateButton;

    public ServiceTM() {
    }

    public ServiceTM(String sevId, String description, String name, double price, double discount, JFXButton deleteButton, JFXButton updateButton) {
        this.sevId = sevId;
        this.description = description;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.deleteButton = deleteButton;
        this.updateButton = updateButton;
    }

    public String getSevId() {
        return sevId;
    }

    public void setSevId(String sevId) {
        this.sevId = sevId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public JFXButton getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(JFXButton updateButton) {
        this.updateButton = updateButton;
    }
}
