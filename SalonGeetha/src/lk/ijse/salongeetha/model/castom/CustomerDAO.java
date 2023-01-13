package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends SQLUtil<Customer> {
    ArrayList<Customer> searchCustomerDetails(Customer customer) throws SQLException, ClassNotFoundException;
}
