package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.EmployeeDTO;
import lk.ijse.salongeetha.to.UserDTO;

import java.sql.SQLException;

public interface UserDAO extends SQLUtil<UserDTO> {
    boolean delete(UserDTO userDTO, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;
}
