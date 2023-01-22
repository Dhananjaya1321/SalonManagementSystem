package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.UserDAO;
import lk.ijse.salongeetha.entity.Employee;
import lk.ijse.salongeetha.entity.User;
import lk.ijse.salongeetha.util.SetPassword;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    public boolean add(User user) throws SQLException, ClassNotFoundException {
        String password = SetPassword.setPassword() + user.getPassword();//set password
        return CrudUtil.setQuery("INSERT INTO user VALUES (?,?,?)", user.getUserName(), user.getEid(), password);
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
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<User> search(boolean value, User user) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean delete(User user, Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM user WHERE Emp_Id=?", user.getEid());
    }
}
