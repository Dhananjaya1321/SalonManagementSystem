package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.dto.UserDTO;

import java.sql.SQLException;

public interface ForgotPasswordBO extends SuperBOImpl {
    boolean checkEmail(UserDTO userDTO) throws SQLException, ClassNotFoundException;
}
