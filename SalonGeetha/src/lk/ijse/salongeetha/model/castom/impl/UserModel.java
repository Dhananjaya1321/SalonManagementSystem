package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.model.castom.UserDAO;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.User;
import lk.ijse.salongeetha.util.SetPassword;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserModel implements UserDAO {

    public boolean add(User user) throws SQLException, ClassNotFoundException {
        String password= SetPassword.setPassword()+user.getPassword();//set password
        return CrudUtil.setQuery("INSERT INTO User VALUES (?,?,?)",user.getUserName(),user.getEid(),password);
    }

    @Override
    public boolean delete(User user) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(User supplier) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<User> search(User supplier) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean delete(User user, Employee employee) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            boolean executeUpdate = CrudUtil.setQuery("DELETE FROM User WHERE Emp_Id=?", user.getEid());
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
