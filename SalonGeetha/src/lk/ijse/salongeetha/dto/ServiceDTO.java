package lk.ijse.salongeetha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceDTO {
    private String sevId;
    private String description;
    private String name;
    private double price;
    private double discount;

}
