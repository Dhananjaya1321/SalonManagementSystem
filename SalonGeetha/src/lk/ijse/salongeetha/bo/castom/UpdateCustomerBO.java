package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.Customer;

import java.sql.SQLException;

public interface UpdateCustomerBO extends SuperBOImpl{
    boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException;
}
