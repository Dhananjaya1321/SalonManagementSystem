package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.AddCustomerBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.CustomerDAO;
import lk.ijse.salongeetha.dao.castom.impl.CustomerDAOImpl;
import lk.ijse.salongeetha.to.Customer;

import java.sql.SQLException;

public class AddCustomerBOImpl implements AddCustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.CUSTOMER);

    @Override
    public boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        return customerDAO.add(customer);
    }
}
