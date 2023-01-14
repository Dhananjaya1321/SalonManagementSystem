package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.to.Customer;

import java.sql.SQLException;

public interface AddCustomerBO {
    boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException;
}
