package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.EmployeeDTO;
import lk.ijse.salongeetha.to.EmployeeServiceDetailDTO;
import lk.ijse.salongeetha.to.ServiceDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EmployeeServiceBO extends SuperBOImpl {
    boolean checkAlreadyExists(EmployeeServiceDetailDTO employeeServiceDetailDTO) throws SQLException, ClassNotFoundException;

    boolean addEmployeeAndServiceDetail(EmployeeServiceDetailDTO employeeServiceDetailDTO) throws SQLException, ClassNotFoundException;

    ResultSet searchEmployee(boolean value, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    ResultSet searchService(boolean value, ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException;

    boolean deleteEmployeeAndServiceDetail(EmployeeServiceDetailDTO employeeServiceDetailDTO) throws SQLException, ClassNotFoundException;

    ResultSet getAllService() throws SQLException, ClassNotFoundException;

    ResultSet getAllEmployee() throws SQLException, ClassNotFoundException;

    ResultSet getAllEmployeeServiceDetails() throws SQLException, ClassNotFoundException;
}
