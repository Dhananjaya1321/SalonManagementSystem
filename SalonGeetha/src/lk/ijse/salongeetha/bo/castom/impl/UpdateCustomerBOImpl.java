package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.UpdateCustomerBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.CustomerDAO;
import lk.ijse.salongeetha.entity.Customer;
import lk.ijse.salongeetha.dto.CustomerDTO;

import java.sql.SQLException;

public class UpdateCustomerBOImpl implements UpdateCustomerBO {
    CustomerDAO customerDAO= (CustomerDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.CUSTOMER);
    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getNic(),customerDTO.getName(),customerDTO.getPhoneNumber()
        ,customerDTO.getEmail(),customerDTO.getDob(),customerDTO.getUserName()));
    }
}

