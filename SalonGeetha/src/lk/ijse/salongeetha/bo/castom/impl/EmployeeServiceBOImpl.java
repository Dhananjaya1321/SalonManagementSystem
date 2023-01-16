package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.EmployeeServiceBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.dao.castom.EmployeeServiceDAO;
import lk.ijse.salongeetha.dao.castom.QueryDAO;
import lk.ijse.salongeetha.dao.castom.ServiceDAO;
import lk.ijse.salongeetha.to.EmployeeDTO;
import lk.ijse.salongeetha.to.EmployeeServiceDetailDTO;
import lk.ijse.salongeetha.to.ServiceDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeServiceBOImpl implements EmployeeServiceBO {
    ServiceDAO serviceDAO = (ServiceDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.SERVICE);
    EmployeeDAO employeeDAO = (EmployeeDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.EMPLOYEE);
    EmployeeServiceDAO employeeServiceDAO = (EmployeeServiceDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.EMPLOYEE_SERVICE);
    QueryDAO queryDAO = (QueryDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.QUERY);

    @Override
    public boolean checkAlreadyExists(EmployeeServiceDetailDTO employeeServiceDetailDTO) throws SQLException, ClassNotFoundException {
        return employeeServiceDAO.checkAlreadyExists(employeeServiceDetailDTO);
    }


    @Override
    public boolean addEmployeeAndServiceDetail(EmployeeServiceDetailDTO employeeServiceDetailDTO) throws SQLException, ClassNotFoundException {
        return employeeServiceDAO.add(employeeServiceDetailDTO);
    }

    @Override
    public ResultSet searchEmployee(boolean value, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(value, employeeDTO);
    }

    @Override
    public ResultSet searchService(boolean value, ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException {
        return serviceDAO.search(value, serviceDTO);
    }

    @Override
    public boolean deleteEmployeeAndServiceDetail(EmployeeServiceDetailDTO employeeServiceDetailDTO) throws SQLException, ClassNotFoundException {
        return employeeServiceDAO.delete(employeeServiceDetailDTO);
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
