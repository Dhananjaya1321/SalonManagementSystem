package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.EmployeeBO;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.dao.castom.UserDAO;
import lk.ijse.salongeetha.dao.castom.impl.EmployeeDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.UserDAOImpl;
import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.to.EmployeeDTO;
import lk.ijse.salongeetha.to.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeBOImpl implements EmployeeBO {
    UserDAO userDAO = new UserDAOImpl();
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    public boolean addReceptionist(EmployeeDTO employeeDTO, UserDTO userDTO) throws SQLException, ClassNotFoundException{
        try {
            DBConnection.getDBConnection().getConnection().setAutoCommit(false);
            boolean isAdded = employeeDAO.addReceptionist(employeeDTO, userDTO);
            if (isAdded) {
                UserDAO userDAO = new UserDAOImpl();
                boolean addUser = userDAO.add(userDTO);
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
    public boolean deleteReceptionist(EmployeeDTO employeeDTO, UserDTO userDTO) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            boolean executeUpdate = userDAO.delete(userDTO, employeeDTO);
            if (executeUpdate) {
                boolean deleteEmployee = employeeDAO.delete(employeeDTO);
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
    public boolean addEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(employeeDTO);
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(employeeDTO);
    }

    @Override
    public boolean deleteEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(employeeDTO);
    }

    @Override
    public String checkIdEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.checkId();
    }

    @Override
    public ResultSet searchEmployee(boolean value, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(value, employeeDTO);
    }

    @Override
    public ResultSet getAllEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAll();
    }
}
