package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.CustomerBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.CustomerDAO;
import lk.ijse.salongeetha.dao.castom.impl.CustomerDAOImpl;
import lk.ijse.salongeetha.to.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.CUSTOMER);
    @Override
    public boolean deleteCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(customer);
    }
    @Override
    public ResultSet searchCustomer(boolean value, Customer customer) throws SQLException, ClassNotFoundException {
        return customerDAO.search(value,customer);
    }
    @Override
    public ResultSet getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }
}
