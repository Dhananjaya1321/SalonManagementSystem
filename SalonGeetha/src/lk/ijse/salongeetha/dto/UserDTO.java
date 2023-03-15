package lk.ijse.salongeetha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String userName;
    private String eid;
    private String name;
    private String password;
    private String email;

    public UserDTO(String userName, String eid, String password) {
        this.userName = userName;
        this.eid = eid;
        this.password = password;
    }
}
