package lk.ijse.salongeetha.model.castom;

import lk.ijse.salongeetha.model.SQLUtil;
import lk.ijse.salongeetha.to.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends SQLUtil<Customer> {
    ResultSet searchCustomerDetails(Customer customer) throws SQLException, ClassNotFoundException;
}
