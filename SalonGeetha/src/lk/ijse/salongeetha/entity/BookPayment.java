package lk.ijse.salongeetha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookPayment {
    private String pay_Id;
    private String payment_method;
    private String nic;
    private double amount_due;
    private String bok_Id;
}
