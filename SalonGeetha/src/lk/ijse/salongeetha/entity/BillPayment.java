package lk.ijse.salongeetha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BillPayment {
    private String bilId;
    private String empId;
    private String date;
    private String description;
    private String title;
    private double amountPaid;
}
