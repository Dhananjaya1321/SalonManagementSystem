package lk.ijse.salongeetha.model.castom;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.model.SQLUtil;
import lk.ijse.salongeetha.model.castom.impl.UserModel;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.User;
import org.hibernate.SQLQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends SQLUtil<Employee> {
    ArrayList<Employee> getBeauticians() throws SQLException, ClassNotFoundException;

    ArrayList<Employee> searchEmployeeDetails(Employee employee) throws SQLException, ClassNotFoundException;


    boolean addReceptionist(Employee employee, User user) throws SQLException, ClassNotFoundException;

    Employee getEmployeeJobTitle(User user) throws SQLException, ClassNotFoundException;

    boolean checkAdmin(Employee employee) throws SQLException, ClassNotFoundException;

    boolean updateAdmin(Employee employee) throws SQLException, ClassNotFoundException;
}
