package lk.ijse.salongeetha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
    private String nic;
    private String name;
    private String phoneNumber;
    private String email;
    private String dob;
    private String userName;
}
