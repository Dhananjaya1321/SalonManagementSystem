package lk.ijse.salongeetha.entity;

public class AppointmentPayment {
    String Pay_Id;
    String Payment_method;
    String NIC;
    double Amount_due;
    String Apt_Id;

    public AppointmentPayment() {
    }

    public AppointmentPayment(String pay_Id, String payment_method, String NIC, double amount_due, String apt_Id) {
        Pay_Id = pay_Id;
        Payment_method = payment_method;
        this.NIC = NIC;
        Amount_due = amount_due;
        Apt_Id = apt_Id;
    }

    @Override
    public String toString() {
        return "AppointmentPayment{" +
                "Pay_Id='" + Pay_Id + '\'' +
                ", Payment_method='" + Payment_method + '\'' +
                ", NIC='" + NIC + '\'' +
                ", Amount_due=" + Amount_due +
                ", Apt_Id='" + Apt_Id + '\'' +
                '}';
    }

    public String getPay_Id() {
        return Pay_Id;
    }

    public void setPay_Id(String pay_Id) {
        Pay_Id = pay_Id;
    }

    public String getPayment_method() {
        return Payment_method;
    }

    public void setPayment_method(String payment_method) {
        Payment_method = payment_method;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public double getAmount_due() {
        return Amount_due;
    }

    public void setAmount_due(double amount_due) {
        Amount_due = amount_due;
    }

    public String getApt_Id() {
        return Apt_Id;
    }

    public void setApt_Id(String apt_Id) {
        Apt_Id = apt_Id;
    }


}
