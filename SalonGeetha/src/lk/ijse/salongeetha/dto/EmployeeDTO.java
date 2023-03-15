package lk.ijse.salongeetha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private String empId;
    private String name;
    private String address;
    private String dob;
    private String phoneNumber;
    private String description;
    private String email;
    private String nic;
    private String jobTitle;
    private String userName;

   /* public EmployeeDTO(String empId, String name, String address, String dob, String phoneNumber, String description,
                       String email, String nic, String jobTitle, String userName) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.email = email;
        this.nic = nic;
        this.jobTitle = jobTitle;
        this.userName = userName;
    }*/

    public EmployeeDTO(String empId, String name, String address, String dob, String phoneNumber, String description,
                       String email, String nic, String jobTitle) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.email = email;
        this.nic = nic;
        this.jobTitle = jobTitle;
    }
}
