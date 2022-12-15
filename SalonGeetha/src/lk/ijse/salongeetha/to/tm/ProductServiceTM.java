package lk.ijse.salongeetha.to.tm;

import com.jfoenix.controls.JFXButton;

public class ProductServiceTM {
    private String proId;
    private String sevId;
    private int qty;
    private String name;
    private JFXButton deleteButton;
    private JFXButton updateButton;

    public ProductServiceTM() {
    }

    public ProductServiceTM(String proId, String sevId, int qty, String name, JFXButton deleteButton) {
        this.proId = proId;
        this.sevId = sevId;
        this.qty = qty;
        this.name = name;
        this.deleteButton = deleteButton;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getSevId() {
        return sevId;
    }

    public void setSevId(String sevId) {
        this.sevId = sevId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
