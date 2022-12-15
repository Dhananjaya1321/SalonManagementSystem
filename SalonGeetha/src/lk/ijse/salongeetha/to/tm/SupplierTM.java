package lk.ijse.salongeetha.to.tm;

import com.jfoenix.controls.JFXButton;

public class SupplierTM {
    private String supId;
    private String description;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private JFXButton updateButton;
    private JFXButton deleteButton;

    public SupplierTM() {
    }

    public SupplierTM(String supId, String description, String name, String address, String phoneNumber, String email, JFXButton updateButton, JFXButton deleteButton) {
        this.supId = supId;
        this.description = description;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.updateButton = updateButton;
        this.deleteButton = deleteButton;
    }

    public JFXButton getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(JFXButton updateButton) {
        this.updateButton = updateButton;
    }
    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public JFXButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JFXButton deleteButton) {
        this.deleteButton = deleteButton;
    }
}
