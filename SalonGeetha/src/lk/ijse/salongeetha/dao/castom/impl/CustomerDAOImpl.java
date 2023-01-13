package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.CustomerDAO;
import lk.ijse.salongeetha.to.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Customer VALUES (?,?,?,?,?,?)", customer.getNic(), customer.getName(),
                customer.getPhoneNumber(), customer.getEmail(), customer.getDob(), customer.getUserName());
    }

    public boolean delete(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Customer WHERE NIC=?", customer.getNic());
    }

    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Customer SET Name=?,Phone_number=?,Email=?,DOB=? WHERE NIC=?", customer.getName(),
                customer.getPhoneNumber(), customer.getEmail(), customer.getDob(), customer.getNic());
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet search(boolean value, Customer to) throws SQLException, ClassNotFoundException {
        String setColumn;
        if (value) {
            setColumn = "SELECT * FROM Customer WHERE Name LIKE ?";
        } else {
            setColumn = "SELECT * FROM Customer WHERE NIC LIKE ?";
        }
        return CrudUtil.setQuery(setColumn, "%" + to.getName() + "%");
    }

    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM Customer");
    }


    public ResultSet searchCustomerDetails(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM Customer WHERE NIC=?", customer.getNic());
    }
}
