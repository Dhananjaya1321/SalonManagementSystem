package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.CustomerDTO;

import java.sql.SQLException;

public interface UpdateCustomerBO extends SuperBOImpl{
    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
}
