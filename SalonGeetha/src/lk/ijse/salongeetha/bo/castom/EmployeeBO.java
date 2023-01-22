package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.dto.EmployeeDTO;
import lk.ijse.salongeetha.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBOImpl {

    boolean addReceptionist(EmployeeDTO employeeDTO, UserDTO userDTO) throws SQLException, ClassNotFoundException;

    boolean deleteReceptionist(EmployeeDTO employeeDTO, UserDTO userDTO) throws SQLException, ClassNotFoundException;

    boolean addEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    String checkIdEmployee() throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDTO> searchEmployee(boolean value, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;
}
