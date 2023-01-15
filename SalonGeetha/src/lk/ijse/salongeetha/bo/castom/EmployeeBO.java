package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EmployeeBO extends SuperBOImpl {

    boolean addReceptionist(Employee employee, User user) throws SQLException, ClassNotFoundException;

    boolean deleteReceptionist(Employee employee, User user) throws SQLException, ClassNotFoundException;

    boolean addEmployee(Employee employee) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(Employee employee) throws SQLException, ClassNotFoundException;

    String checkIdEmployee() throws SQLException, ClassNotFoundException;

    ResultSet searchEmployee(boolean value, Employee employee) throws SQLException, ClassNotFoundException;

    ResultSet getAllEmployee() throws SQLException, ClassNotFoundException;
}
