package lk.ijse.salongeetha.to.tm;

import com.jfoenix.controls.JFXButton;

public class PaymentTM {
    private String payId;
    private String paymentMethod;
    private String status;
    private double Amount_due;
    private String AOrB_Id;
    private JFXButton deleteButton;
    private JFXButton updateButton;

    public PaymentTM() {
    }

    public PaymentTM(String payId, String paymentMethod, double amount_due, String AOrB_Id) {
        this.payId = payId;
        this.paymentMethod = paymentMethod;
        Amount_due = amount_due;
        this.AOrB_Id = AOrB_Id;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount_due() {
        return Amount_due;
    }

    public void setAmount_due(double amount_due) {
        Amount_due = amount_due;
    }

    public String getAOrB_Id() {
        return AOrB_Id;
    }

    public void setAOrB_Id(String AOrB_Id) {
        this.AOrB_Id = AOrB_Id;
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
