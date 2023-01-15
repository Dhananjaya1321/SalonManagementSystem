package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.dao.castom.UserDAO;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.User;
import lk.ijse.salongeetha.util.SetPassword;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    public boolean add(User user) throws SQLException, ClassNotFoundException {
        String password = SetPassword.setPassword() + user.getPassword();//set password
        return CrudUtil.setQuery("INSERT INTO User VALUES (?,?,?)", user.getUserName(), user.getEid(), password);
    }

    @Override
    public boolean delete(User user) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
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
    public ResultSet search(boolean value, User supplier) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean delete(User user, Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM User WHERE Emp_Id=?", user.getEid());
    }
}
