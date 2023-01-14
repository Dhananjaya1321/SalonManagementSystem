package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.User;

import java.sql.SQLException;

public interface LoginFormBO {
    boolean addAdminDetails(User user, Employee employee) throws SQLException, ClassNotFoundException;

    boolean setUserAccount(User user) throws SQLException, ClassNotFoundException;

    Employee getEmployeeJobTitle(User user) throws SQLException, ClassNotFoundException;

    boolean checkUserAccount() throws SQLException, ClassNotFoundException;
}
