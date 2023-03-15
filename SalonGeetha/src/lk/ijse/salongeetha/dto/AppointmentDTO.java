package lk.ijse.salongeetha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDTO {
    private String aptId;
    private String date;
    private String time;
    private String nic;
    private String Status;

    public AppointmentDTO(String aptId, String date, String time, String nic) {
        this.aptId = aptId;
        this.date = date;
        this.time = time;
        this.nic = nic;
    }

}
