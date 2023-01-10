package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.User;
import lk.ijse.salongeetha.util.SetPassword;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    public static boolean addAdminUser(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("INSERT INTO User VALUES (?,?,?)");
        preparedStatement.setObject(1, user.getUserName());
        preparedStatement.setObject(2, user.getEid());

        String password= SetPassword.setPassword()+user.getPassword();//set password

        preparedStatement.setObject(3, password);
        int executeUpdate = preparedStatement.executeUpdate();
        if (executeUpdate > 0) {
            return true;
        }
        return false;
    }

    public static boolean addAdminDetails(User user, Employee employee) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                    .prepareStatement("INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setObject(1, "E001");
            preparedStatement.setObject(2, employee.getName());
            preparedStatement.setObject(3, "");
            preparedStatement.setObject(4, "2022-02-02");
            preparedStatement.setObject(5, "");
            preparedStatement.setObject(6, "");
            preparedStatement.setObject(7, employee.getEmail());
            preparedStatement.setObject(8, employee.getNic());
            preparedStatement.setObject(9, "Admin");
            int executeUpdate = preparedStatement.executeUpdate();
            if (executeUpdate > 0) {
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
        PreparedStatement preparedStatement = DBConnection.getDBConnection()
                .getConnection().prepareStatement("SELECT * FROM User");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    public static boolean checkEmail(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT e.Email FROM User u JOIN Employee e ON e.Emp_Id = u.Emp_Id WHERE u.User_name=?");
        preparedStatement.setObject(1, user.getUserName());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user.setEmail((String) resultSet.getObject(1));
            return true;
        }
        return false;
    }

    public static boolean ChangePassword(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("UPDATE User SET Password=? WHERE User_name=?");

        String password= SetPassword.setPassword()+user.getPassword();//set password

        preparedStatement.setObject(1, password);
        preparedStatement.setObject(2, user.getUserName());
        int executeUpdate = preparedStatement.executeUpdate();
        if (executeUpdate > 0) {
            return true;
        }

        return false;
    }

    public static boolean setUserAccount(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT Password FROM User WHERE User_name=?");
        preparedStatement.setObject(1, user.getUserName());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user.setPassword(resultSet.getString(1).substring(2));
            return true;
        }
        return false;
    }
}
