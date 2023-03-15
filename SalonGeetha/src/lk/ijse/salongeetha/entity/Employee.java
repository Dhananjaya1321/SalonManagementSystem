package lk.ijse.salongeetha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    private String empId;
    private String name;
    private String address;
    private String dob;
    private String phoneNumber;
    private String description;
    private String email;
    private String nic;
    private String jobTitle;
}
