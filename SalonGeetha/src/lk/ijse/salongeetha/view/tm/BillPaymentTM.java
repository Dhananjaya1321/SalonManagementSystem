package lk.ijse.salongeetha.view.tm;

import com.jfoenix.controls.JFXButton;

public class BillPaymentTM {
    private String bilId;
    private String empId;
    private String date;
    private String description;
    private String title;
    private double amountPaid;
    private JFXButton deleteButton;
    private JFXButton updateButton;

    public BillPaymentTM() {
    }

    public BillPaymentTM(String bilId, String empId, String date, String description, String title, double amountPaid, JFXButton deleteButton, JFXButton updateButton) {
        this.bilId = bilId;
        this.empId = empId;
        this.date = date;
        this.description = description;
        this.title = title;
        this.amountPaid = amountPaid;
        this.deleteButton = deleteButton;
        this.updateButton = updateButton;
    }

    public String getBilId() {
        return bilId;
    }

    public void setBilId(String bilId) {
        this.bilId = bilId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
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
