package lk.ijse.salongeetha.to.tm;

import com.jfoenix.controls.JFXButton;

public class RentalsTM {
    private String rntId;
    private String name;
    private String description;
    private int avaliableCount;
    private double pricePreDay;
    private double discount;
    private JFXButton deleteButton;
    private JFXButton updateButton;

    public RentalsTM() {
    }

    public RentalsTM(String rntId, String name, String description, int avaliableCount, double pricePreDay, double discount, JFXButton deleteButton, JFXButton updateButton) {
        this.rntId = rntId;
        this.name = name;
        this.description = description;
        this.avaliableCount = avaliableCount;
        this.pricePreDay = pricePreDay;
        this.discount = discount;
        this.deleteButton = deleteButton;
        this.updateButton = updateButton;
    }

    public String getRntId() {
        return rntId;
    }

    public void setRntId(String rntId) {
        this.rntId = rntId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvaliableCount() {
        return avaliableCount;
    }

    public void setAvaliableCount(int avaliableCount) {
        this.avaliableCount = avaliableCount;
    }

    public double getPricePreDay() {
        return pricePreDay;
    }

    public void setPricePreDay(double pricePreDay) {
        this.pricePreDay = pricePreDay;
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
