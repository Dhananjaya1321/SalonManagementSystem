package lk.ijse.salongeetha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Appointment {
    private String aptId;
    private String date;
    private String time;
    private String nic;
    private String status;
}
