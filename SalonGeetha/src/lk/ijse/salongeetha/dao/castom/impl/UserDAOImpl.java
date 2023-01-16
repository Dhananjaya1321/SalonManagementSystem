package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.dao.castom.UserDAO;
import lk.ijse.salongeetha.to.EmployeeDTO;
import lk.ijse.salongeetha.to.UserDTO;
import lk.ijse.salongeetha.util.SetPassword;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    public boolean add(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        String password = SetPassword.setPassword() + userDTO.getPassword();//set password
        return CrudUtil.setQuery("INSERT INTO user VALUES (?,?,?)", userDTO.getUserName(), userDTO.getEid(), password);
    }

    @Override
    public boolean delete(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(UserDTO supplier) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet search(boolean value, UserDTO supplier) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean delete(UserDTO userDTO, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM user WHERE Emp_Id=?", userDTO.getEid());
    }
}
