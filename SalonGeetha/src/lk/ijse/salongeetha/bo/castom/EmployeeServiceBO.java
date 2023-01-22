package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.dto.EmployeeDTO;
import lk.ijse.salongeetha.dto.EmployeeServiceDetailDTO;
import lk.ijse.salongeetha.dto.ServiceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeServiceBO extends SuperBOImpl {
    boolean checkAlreadyExists(EmployeeServiceDetailDTO employeeServiceDetailDTO) throws SQLException, ClassNotFoundException;

    boolean addEmployeeAndServiceDetail(EmployeeServiceDetailDTO employeeServiceDetailDTO) throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDTO> searchEmployee(boolean value, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    ArrayList<ServiceDTO> searchService(boolean value, ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException;

    boolean deleteEmployeeAndServiceDetail(EmployeeServiceDetailDTO employeeServiceDetailDTO) throws SQLException, ClassNotFoundException;

    ArrayList<ServiceDTO> getAllService() throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeServiceDetailDTO> getAllEmployeeServiceDetails() throws SQLException, ClassNotFoundException;
}
