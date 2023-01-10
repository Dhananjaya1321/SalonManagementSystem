package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.User;
import lk.ijse.salongeetha.util.SetPassword;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    public static boolean addAdminUser(User user) throws SQLException, ClassNotFoundException {
        String password = SetPassword.setPassword() + user.getPassword();//set password
        return CrudUtil.setQuery("INSERT INTO User VALUES (?,?,?)", user.getUserName(), user.getEid(), password);
    }

    public static boolean addAdminDetails(User user, Employee employee) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            boolean isAdded = CrudUtil.setQuery("INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?)", "E001", employee.getName(), ""
                    , "2022-02-02", "", "", employee.getEmail(), employee.getNic(), "Admin");
            if (isAdded) {
                boolean addAdminUser = LoginModel.addAdminUser(user);
                if (addAdminUser) {
                    DBConnection.getDBConnection().getConnection().commit();
                    return true;
                }

            }
            DBConnection.getDBConnection().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getDBConnection().getConnection().setAutoCommit(true);
        }
    }

    public static boolean checkUserAccount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM User");
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    public static boolean checkEmail(User user) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT e.Email FROM User u JOIN Employee e ON e.Emp_Id =" +
                " u.Emp_Id WHERE u.User_name=?", user.getUserName());
        if (resultSet.next()) {
            user.setEmail((String) resultSet.getObject(1));
            return true;
        }
        return false;
    }

    public static boolean ChangePassword(User user) throws SQLException, ClassNotFoundException {
        String password = SetPassword.setPassword() + user.getPassword();//set password
        return CrudUtil.setQuery("UPDATE User SET Password=? WHERE User_name=?",password,user.getUserName());
    }

    public static boolean setUserAccount(User user) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Password FROM User WHERE User_name=?",user.getUserName());
        if (resultSet.next()) {
            user.setPassword(resultSet.getString(1).substring(2));
            return true;
        }
        return false;
    }
}
