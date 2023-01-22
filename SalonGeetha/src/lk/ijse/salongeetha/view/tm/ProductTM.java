package lk.ijse.salongeetha.view.tm;

import com.jfoenix.controls.JFXButton;

public class ProductTM {
    private String proId;
    private String description;
    private String category;
    private String brand;
    private String supId;
    private double unitPrice;
    private int qtyOnHand;
    private JFXButton deleteButton;
    private JFXButton updateButton;

    public ProductTM() {
    }

    public ProductTM(String proId, String description, String category, String brand, String supId, double unitPrice, int qtyOnHand, JFXButton deleteButton, JFXButton updateButton) {
        this.proId = proId;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.supId = supId;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
        this.deleteButton = deleteButton;
        this.updateButton = updateButton;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
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
