package lk.ijse.salongeetha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentPayment {
    private String Pay_Id;
    private String Payment_method;
    private String NIC;
    private double Amount_due;
    private String Apt_Id;
}
