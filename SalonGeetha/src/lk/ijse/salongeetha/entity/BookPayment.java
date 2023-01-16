package lk.ijse.salongeetha.entity;

public class BookPayment {
    String Pay_Id;
    String Payment_method;
    String NIC;
    double Amount_due;
    String Bok_Id;

    public BookPayment() {
    }

    public BookPayment(String pay_Id, String payment_method, String NIC, double amount_due, String bok_Id) {
        Pay_Id = pay_Id;
        Payment_method = payment_method;
        this.NIC = NIC;
        Amount_due = amount_due;
        Bok_Id = bok_Id;
    }

    @Override
    public String toString() {
        return "BookPayment{" +
                "Pay_Id='" + Pay_Id + '\'' +
                ", Payment_method='" + Payment_method + '\'' +
                ", NIC='" + NIC + '\'' +
                ", Amount_due=" + Amount_due +
                ", Bok_Id='" + Bok_Id + '\'' +
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

    public String getBok_Id() {
        return Bok_Id;
    }

    public void setBok_Id(String bok_Id) {
        Bok_Id = bok_Id;
    }
}
