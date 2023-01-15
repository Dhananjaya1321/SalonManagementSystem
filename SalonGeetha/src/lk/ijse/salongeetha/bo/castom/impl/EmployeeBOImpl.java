package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.EmployeeBO;
import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.dao.castom.UserDAO;
import lk.ijse.salongeetha.dao.castom.impl.EmployeeDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.UserModel;
import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeBOImpl implements EmployeeBO {
    UserDAO userDAO = new UserModel();
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    public boolean addReceptionist(Employee employee, User user) throws SQLException, ClassNotFoundException{
        try {
            DBConnection.getDBConnection().getConnection().setAutoCommit(false);
            boolean isAdded = employeeDAO.addReceptionist(employee, user);
            if (isAdded) {
                UserDAO userDAO = new UserModel();
                boolean addUser = userDAO.add(user);
                if (addUser) {
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

    @Override
    public boolean deleteReceptionist(Employee employee, User user) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            boolean executeUpdate = userDAO.delete(user, employee);
            if (executeUpdate) {
                boolean deleteEmployee = employeeDAO.delete(employee);
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
