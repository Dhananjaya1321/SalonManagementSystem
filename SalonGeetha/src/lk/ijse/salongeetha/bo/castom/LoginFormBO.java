package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.EmployeeDTO;
import lk.ijse.salongeetha.to.UserDTO;

import java.sql.SQLException;

public interface LoginFormBO extends SuperBOImpl {
    boolean addAdminDetails(UserDTO userDTO, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean setUserAccount(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    EmployeeDTO getEmployeeJobTitle(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    boolean checkUserAccount() throws SQLException, ClassNotFoundException;
}
