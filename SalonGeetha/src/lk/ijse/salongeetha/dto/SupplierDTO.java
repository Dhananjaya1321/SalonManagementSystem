package lk.ijse.salongeetha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDTO {
    private String supId;
    private String description;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

}
