package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.Customer;

import java.sql.SQLException;

public interface AddCustomerBO extends SuperBOImpl {
    boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException;
}
