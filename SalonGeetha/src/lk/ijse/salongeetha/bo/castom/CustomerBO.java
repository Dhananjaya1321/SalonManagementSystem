package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerBO extends SuperBOImpl {
    boolean deleteCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    ResultSet searchCustomer(boolean value, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    ResultSet getAllCustomer() throws SQLException, ClassNotFoundException;
}
