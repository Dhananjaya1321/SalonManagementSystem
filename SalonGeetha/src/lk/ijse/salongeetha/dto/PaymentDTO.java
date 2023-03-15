package lk.ijse.salongeetha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
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

}
