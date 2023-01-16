package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.EmployeeDTO;
import lk.ijse.salongeetha.to.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends SQLUtil<EmployeeDTO> {
    ArrayList<EmployeeDTO> getBeauticians() throws SQLException, ClassNotFoundException;

    ResultSet searchEmployeeDetails(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;


    boolean addReceptionist(EmployeeDTO employeeDTO, UserDTO userDTO) throws SQLException, ClassNotFoundException;

    EmployeeDTO getEmployeeJobTitle(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    boolean checkAdmin(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean updateAdmin(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;
}
