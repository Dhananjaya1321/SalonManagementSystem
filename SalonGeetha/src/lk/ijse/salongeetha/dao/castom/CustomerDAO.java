package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerDAO extends SQLUtil<Customer> {
    ResultSet searchCustomerDetails(Customer customer) throws SQLException, ClassNotFoundException;
}
