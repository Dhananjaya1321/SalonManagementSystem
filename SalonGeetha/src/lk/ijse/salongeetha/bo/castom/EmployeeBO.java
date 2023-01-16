package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.EmployeeDTO;
import lk.ijse.salongeetha.to.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EmployeeBO extends SuperBOImpl {

    boolean addReceptionist(EmployeeDTO employeeDTO, UserDTO userDTO) throws SQLException, ClassNotFoundException;

    boolean deleteReceptionist(EmployeeDTO employeeDTO, UserDTO userDTO) throws SQLException, ClassNotFoundException;

    boolean addEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    String checkIdEmployee() throws SQLException, ClassNotFoundException;

    ResultSet searchEmployee(boolean value, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    ResultSet getAllEmployee() throws SQLException, ClassNotFoundException;
}
