package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.EmployeeServiceDetail;
import lk.ijse.salongeetha.to.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EmployeeServiceBO extends SuperBOImpl {
    boolean checkAlreadyExists(EmployeeServiceDetail employeeServiceDetail) throws SQLException, ClassNotFoundException;

    boolean addEmployeeAndServiceDetail(EmployeeServiceDetail employeeServiceDetail) throws SQLException, ClassNotFoundException;

    ResultSet searchEmployee(boolean value, Employee employee) throws SQLException, ClassNotFoundException;

    ResultSet searchService(boolean value, Service service) throws SQLException, ClassNotFoundException;

    boolean deleteEmployeeAndServiceDetail(EmployeeServiceDetail employeeServiceDetail) throws SQLException, ClassNotFoundException;

    ResultSet getAllService() throws SQLException, ClassNotFoundException;

    ResultSet getAllEmployee() throws SQLException, ClassNotFoundException;

    ResultSet getAllEmployeeServiceDetails() throws SQLException, ClassNotFoundException;
}
