package lk.ijse.salongeetha.model;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.User;
import lk.ijse.salongeetha.util.SetPassword;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserModel {

    public static boolean addUser(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection().
                prepareStatement("INSERT INTO User VALUES (?,?,?)");
        preparedStatement.setObject(1, user.getUserName());
        preparedStatement.setObject(2, user.getEid());

        String password= SetPassword.setPassword()+user.getPassword();//set password

        preparedStatement.setObject(3,password);
        boolean executeUpdate = preparedStatement.executeUpdate() > 0;
        if (executeUpdate) {
            return true;
        }
        return false;
    }

    public static boolean deleteUser(User user, Employee employee) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection()
                    .prepareStatement("DELETE FROM User WHERE Emp_Id=?");
            prepareStatement.setObject(1, user.getEid());
//            System.out.println(user.getEid());
            boolean executeUpdate = prepareStatement.executeUpdate() > 0;
            if (executeUpdate) {
                boolean deleteEmployee = EmployeeModel.deleteEmployee(employee);
                if (deleteEmployee) {
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
}
