package lk.ijse.salongeetha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {
    private String bokId;
    private String date;
    private String time;
    private String nic;
    private String status;

    public BookDTO(String bokId, String date, String nic) {
        this.bokId = bokId;
        this.date = date;
        this.nic = nic;
    }

}
