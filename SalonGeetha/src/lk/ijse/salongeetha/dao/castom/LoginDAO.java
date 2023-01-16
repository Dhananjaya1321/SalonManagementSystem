package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.EmployeeDTO;
import lk.ijse.salongeetha.to.UserDTO;

import java.sql.SQLException;

public interface LoginDAO extends SQLUtil<UserDTO> {
    boolean ChangePassword(UserDTO userDTO) throws SQLException, ClassNotFoundException;
    boolean setUserAccount(UserDTO userDTO) throws SQLException, ClassNotFoundException;
    boolean checkEmail(UserDTO userDTO) throws SQLException, ClassNotFoundException;
    boolean checkUserAccount() throws SQLException, ClassNotFoundException;
    boolean addDetails(UserDTO userDTO, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;
}
