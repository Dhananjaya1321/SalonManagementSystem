package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerDAO extends SQLUtil<CustomerDTO> {
    ResultSet searchCustomerDetails(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
}
