package lk.ijse.salongeetha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceAppointmentDetailDTO {
    private String aptId;
    private String sevId;
    private double price;
    private double discount;

    public ServiceAppointmentDetailDTO(String aptId, String sevId) {
        this.aptId = aptId;
        this.sevId = sevId;
    }

}
