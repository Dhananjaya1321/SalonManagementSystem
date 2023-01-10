package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.to.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerModel {
    public static boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection().
                prepareStatement("INSERT INTO Customer VALUES (?,?,?,?,?,?)");
        preparedStatement.setObject(1, customer.getNic());
        preparedStatement.setObject(2, customer.getName());
        preparedStatement.setObject(3, customer.getPhoneNumber());
        preparedStatement.setObject(4, customer.getEmail());
        preparedStatement.setObject(5, customer.getDob());
        preparedStatement.setObject(6, customer.getUserName());
        int executeUpdate = preparedStatement.executeUpdate();
        if (executeUpdate > 0) {
            return true;
        }
        return false;
    }

    public static boolean deleteCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("DELETE FROM Customer WHERE NIC=?");
        preparedStatement.setObject(1,customer.getNic());
        int executeUpdate = preparedStatement.executeUpdate();
        if (executeUpdate > 0) {
            return true;
        }
        return false;
    }
    public static boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("UPDATE Customer SET Name=?,Phone_number=?,Email=?,DOB=? WHERE NIC=?");
        preparedStatement.setObject(1,customer.getName());
        preparedStatement.setObject(2,customer.getPhoneNumber());
        preparedStatement.setObject(3,customer.getEmail());
        preparedStatement.setObject(4,customer.getDob());
        preparedStatement.setObject(5,customer.getNic());
        int executeUpdate = preparedStatement.executeUpdate();
        if(executeUpdate > 0){
            return true;
        }
        return false;
    }

    public static ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = new ArrayList<>();
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT * FROM Customer");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Customer customer=new Customer();
            customer.setNic(String.valueOf(resultSet.getObject(1)));
            customer.setName(String.valueOf(resultSet.getObject(2)));
            customer.setPhoneNumber(String.valueOf(resultSet.getObject(3)));
            customer.setEmail(String.valueOf(resultSet.getObject(4)));
            customer.setDob(String.valueOf(resultSet.getObject(5)));
            customers.add(customer);
        }
        return customers;
    }

    public static ArrayList<Customer> searchCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers=new ArrayList<>();
        String setColumn;
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(customer.getName());
        if (matcher.matches()) {
            setColumn="SELECT * FROM Customer WHERE Name LIKE ?";
        }else {
            setColumn="SELECT * FROM Customer WHERE NIC LIKE ?";
        }
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection().prepareStatement(setColumn);
        prepareStatement.setObject(1,"%"+customer.getName()+"%");
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            Customer searchCustomer=new Customer();
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

    public static ArrayList<Customer> searchCustomerDetails(Customer customer) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers=new ArrayList<>();
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection().prepareStatement("SELECT * FROM Customer WHERE NIC=?");
        prepareStatement.setObject(1,customer.getNic());
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            Customer searchCustomer=new Customer();
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
}
