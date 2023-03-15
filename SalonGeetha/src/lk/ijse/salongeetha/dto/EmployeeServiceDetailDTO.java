package lk.ijse.salongeetha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeServiceDetailDTO {
    private String empId;
    private String sevId;
    private String sevName;
    private String empName;
}
