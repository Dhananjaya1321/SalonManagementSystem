package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.EmployeeBO;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.dao.castom.UserDAO;
import lk.ijse.salongeetha.dao.castom.impl.EmployeeDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.UserModel;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeBOImpl implements EmployeeBO {
    UserDAO userDAO = new UserModel();
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    public boolean addReceptionist(Employee employee, User user) throws SQLException, ClassNotFoundException {
        return employeeDAO.addReceptionist(employee, user);
    }

    @Override
    public boolean deleteReceptionist(Employee employee, User user) throws SQLException, ClassNotFoundException {
        return userDAO.delete(user, employee);
    }

    @Override
    public boolean addEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(employee);
    }

    @Override
    public boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(employee);
    }

    @Override
    public boolean deleteEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(employee);
    }

    @Override
    public String checkIdEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.checkId();
    }

    @Override
    public ResultSet searchEmployee(boolean value, Employee employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(value, employee);
    }

    @Override
    public ResultSet getAllEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAll();
    }
}
