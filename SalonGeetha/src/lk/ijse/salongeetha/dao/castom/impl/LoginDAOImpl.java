package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.LoginDAO;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.User;
import lk.ijse.salongeetha.util.SetPassword;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {
    public boolean add(User user) throws SQLException, ClassNotFoundException {
        String password = SetPassword.setPassword() + user.getPassword();//set password
        return CrudUtil.setQuery("INSERT INTO User VALUES (?,?,?)", user.getUserName(), user.getEid(), password);
    }


    public boolean addDetails(User user, Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?)", "E001", employee.getName(), ""
                    , "2022-02-02", "", "", employee.getEmail(), employee.getNic(), "Admin");
    }

    public boolean checkUserAccount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM User");
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    public boolean checkEmail(User user) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT e.Email FROM User u JOIN Employee e ON e.Emp_Id =" +
                " u.Emp_Id WHERE u.User_name=?", user.getUserName());
        if (resultSet.next()) {
            user.setEmail((String) resultSet.getObject(1));
            return true;
        }
        return false;
    }

    public boolean ChangePassword(User user) throws SQLException, ClassNotFoundException {
        String password = SetPassword.setPassword() + user.getPassword();//set password
        return CrudUtil.setQuery("UPDATE User SET Password=? WHERE User_name=?",password,user.getUserName());
    }

    public boolean setUserAccount(User user) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Password FROM User WHERE User_name=?",user.getUserName());
        if (resultSet.next()) {
            user.setPassword(resultSet.getString(1).substring(2));
            return true;
        }
        return false;
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
    public ResultSet search(boolean value, User to) throws SQLException, ClassNotFoundException {
        return null;
    }


}
