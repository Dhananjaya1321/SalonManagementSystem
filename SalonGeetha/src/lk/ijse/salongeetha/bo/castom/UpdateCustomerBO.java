package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.to.Customer;

import java.sql.SQLException;

public interface UpdateCustomerBO {
    boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException;
}
