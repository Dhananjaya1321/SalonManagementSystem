package lk.ijse.salongeetha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private String nic;
    private String name;
    private String phoneNumber;
    private String email;
    private String dob;
    private String userName;

    public CustomerDTO(String nic, String name, String phoneNumber, String email, String dob) {
        this.nic = nic;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dob = dob;
    }
}
