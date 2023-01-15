package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.UpdateCustomerBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.CustomerDAO;
import lk.ijse.salongeetha.dao.castom.impl.CustomerDAOImpl;
import lk.ijse.salongeetha.to.Customer;

import java.sql.SQLException;

public class UpdateCustomerBOImpl implements UpdateCustomerBO {
    CustomerDAO customerDAO= (CustomerDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.CUSTOMER);
    @Override
    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        return customerDAO.update(customer);
    }
}

