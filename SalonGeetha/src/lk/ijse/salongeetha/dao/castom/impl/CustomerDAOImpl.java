package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.CustomerDAO;
import lk.ijse.salongeetha.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public ArrayList<Customer> search(boolean value, Customer to) throws SQLException, ClassNotFoundException {
        String setColumn;
        if (value) {
            setColumn = "SELECT * FROM Customer WHERE Name LIKE ?";
        } else {
            setColumn = "SELECT * FROM Customer WHERE NIC LIKE ?";
        }
        ArrayList<Customer> customers = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery(setColumn, "%" + to.getName() + "%");
        while (resultSet.next()) {
            customers.add(
                    new Customer(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return customers;
    }

    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM Customer");
        while (resultSet.next()) {
            customers.add(
                    new Customer(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return customers;
    }


    public ArrayList<Customer> searchCustomerDetails(Customer customer) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM Customer WHERE NIC=?", customer.getNic());
        while (resultSet.next()) {
            customers.add(
                    new Customer(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return customers;
    }
}
