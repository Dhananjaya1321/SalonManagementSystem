package lk.ijse.salongeetha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillPaymentDTO {
    private String bilId;
    private String empId;
    private String date;
    private String description;
    private String title;
    private double amountPaid;
}
