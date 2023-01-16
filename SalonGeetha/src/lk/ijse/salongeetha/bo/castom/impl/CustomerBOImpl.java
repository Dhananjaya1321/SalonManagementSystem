package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.CustomerBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.CustomerDAO;
import lk.ijse.salongeetha.to.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.CUSTOMER);
    @Override
    public boolean deleteCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(customerDTO);
    }
    @Override
    public ResultSet searchCustomer(boolean value, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.search(value, customerDTO);
    }
    @Override
    public ResultSet getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }
}
