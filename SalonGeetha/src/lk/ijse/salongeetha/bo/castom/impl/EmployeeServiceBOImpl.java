package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.EmployeeServiceBO;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.dao.castom.EmployeeServiceDAO;
import lk.ijse.salongeetha.dao.castom.QueryDAO;
import lk.ijse.salongeetha.dao.castom.ServiceDAO;
import lk.ijse.salongeetha.dao.castom.impl.EmployeeDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.EmployeeServiceDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.QueryDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.ServiceDAOImpl;
import lk.ijse.salongeetha.to.Employee;
import lk.ijse.salongeetha.to.EmployeeServiceDetail;
import lk.ijse.salongeetha.to.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeServiceBOImpl implements EmployeeServiceBO {
    ServiceDAO serviceDAO = new ServiceDAOImpl();
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    EmployeeServiceDAO employeeServiceDAO = new EmployeeServiceDAOImpl();
    QueryDAO queryDAO = new QueryDAOImpl();

    @Override
    public boolean checkAlreadyExists(EmployeeServiceDetail employeeServiceDetail) throws SQLException, ClassNotFoundException {
        return employeeServiceDAO.checkAlreadyExists(employeeServiceDetail);
    }


    @Override
    public boolean addEmployeeAndServiceDetail(EmployeeServiceDetail employeeServiceDetail) throws SQLException, ClassNotFoundException {
        return employeeServiceDAO.add(employeeServiceDetail);
    }

    @Override
    public ResultSet searchEmployee(boolean value, Employee employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(value, employee);
    }

    @Override
    public ResultSet searchService(boolean value, Service service) throws SQLException, ClassNotFoundException {
        return serviceDAO.search(value, service);
    }

    @Override
    public boolean deleteEmployeeAndServiceDetail(EmployeeServiceDetail employeeServiceDetail) throws SQLException, ClassNotFoundException {
        return employeeServiceDAO.delete(employeeServiceDetail);
    }

    @Override
    public ResultSet getAllService() throws SQLException, ClassNotFoundException {
        return serviceDAO.getAll();
    }

    @Override
    public ResultSet getAllEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAll();
    }

    @Override
    public ResultSet getAllEmployeeServiceDetails() throws SQLException, ClassNotFoundException {
        return queryDAO.getAllEmployeeServiceDetails();
    }

}
