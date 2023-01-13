package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends SQLUtil<Employee> {
    ArrayList<Employee> getBeauticians() throws SQLException, ClassNotFoundException;

    ResultSet searchEmployeeDetails(Employee employee) throws SQLException, ClassNotFoundException;


    boolean addReceptionist(Employee employee, User user) throws SQLException, ClassNotFoundException;

    Employee getEmployeeJobTitle(User user) throws SQLException, ClassNotFoundException;

    boolean checkAdmin(Employee employee) throws SQLException, ClassNotFoundException;

    boolean updateAdmin(Employee employee) throws SQLException, ClassNotFoundException;
}
