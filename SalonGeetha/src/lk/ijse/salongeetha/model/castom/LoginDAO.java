package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
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
