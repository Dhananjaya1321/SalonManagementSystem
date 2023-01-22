package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.AddCustomerBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.CustomerDAO;
import lk.ijse.salongeetha.entity.Customer;
import lk.ijse.salongeetha.dto.CustomerDTO;

import java.sql.SQLException;

public class AddCustomerBOImpl implements AddCustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.CUSTOMER);

    @Override
    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(dto.getNic(), dto.getName(),dto.getPhoneNumber(), dto.getEmail(), dto.getDob(),
                dto.getUserName()));
    }
}
