package lk.ijse.salongeetha.model.castom;

import lk.ijse.salongeetha.model.SQLUtil;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.User;

import java.sql.SQLException;

public interface LoginDAO extends SQLUtil<User> {
    boolean ChangePassword(User user) throws SQLException, ClassNotFoundException;
    boolean setUserAccount(User user) throws SQLException, ClassNotFoundException;
    boolean checkEmail(User user) throws SQLException, ClassNotFoundException;
    boolean checkUserAccount() throws SQLException, ClassNotFoundException;
    boolean addDetails(User user, Employee employee) throws SQLException, ClassNotFoundException;
}
