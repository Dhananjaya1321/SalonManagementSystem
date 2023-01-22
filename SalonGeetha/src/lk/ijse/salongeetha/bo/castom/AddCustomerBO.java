package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.dto.CustomerDTO;

import java.sql.SQLException;

public interface AddCustomerBO extends SuperBOImpl {
    boolean addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
}
