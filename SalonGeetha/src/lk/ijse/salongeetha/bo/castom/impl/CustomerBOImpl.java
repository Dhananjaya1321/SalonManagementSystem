package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.CustomerBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.CustomerDAO;
import lk.ijse.salongeetha.entity.Customer;
import lk.ijse.salongeetha.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.CUSTOMER);
    private ArrayList<Customer> customers;
    private ArrayList<CustomerDTO> customerDTOS;

    @Override
    public boolean deleteCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(new Customer(dto.getNic(), dto.getName(), dto.getPhoneNumber(), dto.getEmail(),
                dto.getDob(), dto.getUserName()));
    }

    @Override
    public ArrayList<CustomerDTO> searchCustomer(boolean value, CustomerDTO dto) throws SQLException, ClassNotFoundException {
        /*ArrayList<Customer>*/
        customers = customerDAO.search(value, new Customer(dto.getNic(), dto.getName(), dto.getPhoneNumber(), dto.getEmail(),
                dto.getDob(), dto.getUserName()));
        /*ArrayList<CustomerDTO>*/
        customerDTOS = new ArrayList<>();
        for (Customer c : customers) {
            customerDTOS.add(new CustomerDTO(c.getNic(), c.getName(), c.getPhoneNumber(), c.getEmail(),
                    c.getDob(), c.getUserName()));
        }
        return customerDTOS;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        /* ArrayList<Customer>*/
        customers = customerDAO.getAll();
        /*ArrayList<CustomerDTO>*/
        customerDTOS = new ArrayList<>();
        for (Customer c : customers) {
            customerDTOS.add(new CustomerDTO(c.getNic(), c.getName(), c.getPhoneNumber(), c.getEmail(),
                    c.getDob(), c.getUserName()));
        }
        return customerDTOS;
    }
}
