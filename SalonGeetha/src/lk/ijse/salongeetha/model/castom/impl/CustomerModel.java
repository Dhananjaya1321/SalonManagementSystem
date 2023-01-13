package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.CustomerDAO;
import lk.ijse.salongeetha.to.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerModel implements CustomerDAO {
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

    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM Customer");
        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setNic(String.valueOf(resultSet.getObject(1)));
            customer.setName(String.valueOf(resultSet.getObject(2)));
            customer.setPhoneNumber(String.valueOf(resultSet.getObject(3)));
            customer.setEmail(String.valueOf(resultSet.getObject(4)));
            customer.setDob(String.valueOf(resultSet.getObject(5)));
            customers.add(customer);
        }
        return customers;
    }

    public ArrayList<Customer> search(Customer customer) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = new ArrayList<>();
        String setColumn;
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(customer.getName());
        if (matcher.matches()) {
            setColumn = "SELECT * FROM Customer WHERE Name LIKE ?";
        } else {
            setColumn = "SELECT * FROM Customer WHERE NIC LIKE ?";
        }
        ResultSet resultSet = CrudUtil.setQuery(setColumn, "%" + customer.getName() + "%");
        while (resultSet.next()) {
            Customer searchCustomer = new Customer();
            searchCustomer.setNic(String.valueOf(resultSet.getObject(1)));
//            System.out.println("data awo"+String.valueOf(resultSet.getObject(1)));
            searchCustomer.setName(String.valueOf(resultSet.getObject(2)));
            searchCustomer.setPhoneNumber(String.valueOf(resultSet.getObject(3)));
            searchCustomer.setEmail(String.valueOf(resultSet.getObject(4)));
            searchCustomer.setDob(String.valueOf(resultSet.getObject(5)));
            customers.add(searchCustomer);
        }
        return customers;
    }

    public ArrayList<Customer> searchCustomerDetails(Customer customer) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM Customer WHERE NIC=?", customer.getNic());
        while (resultSet.next()) {
            Customer searchCustomer = new Customer();
            searchCustomer.setNic(String.valueOf(resultSet.getObject(1)));
            searchCustomer.setName(String.valueOf(resultSet.getObject(2)));
            searchCustomer.setPhoneNumber(String.valueOf(resultSet.getObject(3)));
            searchCustomer.setEmail(String.valueOf(resultSet.getObject(4)));
            searchCustomer.setDob(String.valueOf(resultSet.getObject(5)));
            customers.add(searchCustomer);
        }
        return customers;
    }
}
