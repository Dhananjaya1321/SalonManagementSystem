package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.LoginFormBO;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.dao.castom.LoginDAO;
import lk.ijse.salongeetha.dao.castom.impl.EmployeeDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.LoginDAOImpl;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.User;

import java.sql.SQLException;

public class LoginFormBOImpl implements LoginFormBO {
    LoginDAO loginDAO = new LoginDAOImpl();
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    public boolean addAdminDetails(User user, Employee employee) throws SQLException, ClassNotFoundException {
        return loginDAO.addDetails(user, employee);
    }
    @Override
    public boolean setUserAccount(User user) throws SQLException, ClassNotFoundException {
        return loginDAO.setUserAccount(user);
    }
    @Override
    public Employee getEmployeeJobTitle(User user) throws SQLException, ClassNotFoundException {
        return employeeDAO.getEmployeeJobTitle(user);
    }
    @Override
    public boolean checkUserAccount() throws SQLException, ClassNotFoundException {
        return loginDAO.checkUserAccount();
    }
}
