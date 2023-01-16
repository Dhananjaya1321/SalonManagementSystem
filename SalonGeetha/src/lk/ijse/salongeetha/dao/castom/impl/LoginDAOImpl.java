package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.LoginDAO;
import lk.ijse.salongeetha.to.EmployeeDTO;
import lk.ijse.salongeetha.to.UserDTO;
import lk.ijse.salongeetha.util.SetPassword;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {
    public boolean add(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        String password = SetPassword.setPassword() + userDTO.getPassword();//set password
        return CrudUtil.setQuery("INSERT INTO User VALUES (?,?,?)", userDTO.getUserName(), userDTO.getEid(), password);
    }


    public boolean addDetails(UserDTO userDTO, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?)", "E001", employeeDTO.getName(), ""
                    , "2022-02-02", "", "", employeeDTO.getEmail(), employeeDTO.getNic(), "Admin");
    }

    public boolean checkUserAccount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM User");
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    public boolean checkEmail(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT e.Email FROM User u JOIN Employee e ON e.Emp_Id =" +
                " u.Emp_Id WHERE u.User_name=?", userDTO.getUserName());
        if (resultSet.next()) {
            userDTO.setEmail((String) resultSet.getObject(1));
            return true;
        }
        return false;
    }

    public boolean ChangePassword(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        String password = SetPassword.setPassword() + userDTO.getPassword();//set password
        return CrudUtil.setQuery("UPDATE User SET Password=? WHERE User_name=?",password, userDTO.getUserName());
    }

    public boolean setUserAccount(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Password FROM User WHERE User_name=?", userDTO.getUserName());
        if (resultSet.next()) {
            userDTO.setPassword(resultSet.getString(1).substring(2));
            return true;
        }
        return false;
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
    public ResultSet search(boolean value, UserDTO to) throws SQLException, ClassNotFoundException {
        return null;
    }


}
