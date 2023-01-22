package lk.ijse.salongeetha.dto;

public class PaymentDTO {
    private String payId;
    private String paymentMethod;
    private String nic;
    private double amountPaid;
    private double balance;
    private String time;
    private String date;
    private String aOrBId;
    private double amountDue;

    public PaymentDTO() {
    }

    public PaymentDTO(String payId, String paymentMethod, String nic, double amountPaid, String aOrBId) {
       this.payId = payId;
        this.paymentMethod = paymentMethod;
        this.nic = nic;
        this.amountPaid = amountPaid;
        this.aOrBId = aOrBId;
    }

   /* public PaymentDTO(String payId, String paymentMethod, String nic, double amountPaid, double amountDue) {
        this.payId = payId;
        this.paymentMethod = paymentMethod;
        this.nic = nic;
        this.amountPaid = amountPaid;
        this.balance = balance;
        this.time = time;
        this.date = date;
        this.amountDue = amountDue;
    }*/

    public String getaOrBId() {
        return aOrBId;
    }

    public void setaOrBId(String aOrBId) {
        this.aOrBId = aOrBId;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "payId='" + payId + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", nic='" + nic + '\'' +
                ", amountPaid=" + amountPaid +
                ", balance=" + balance +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", amountDue=" + amountDue +
                '}';
    }
}
