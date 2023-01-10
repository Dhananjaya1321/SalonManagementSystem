package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.model.castom.SupplierDAO;
import lk.ijse.salongeetha.to.Customer;
import lk.ijse.salongeetha.to.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierModel implements SupplierDAO {
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM Supplier");
        ArrayList<Supplier> suppliers = new ArrayList<>();
        while (resultSet.next()) {
            Supplier supplier = new Supplier();
            supplier.setSupId(String.valueOf(resultSet.getObject(1)));
            supplier.setDescription(String.valueOf(resultSet.getObject(2)));
            supplier.setName(String.valueOf(resultSet.getObject(3)));
            supplier.setAddress(String.valueOf(resultSet.getObject(4)));
            supplier.setPhoneNumber(String.valueOf(resultSet.getObject(5)));
            supplier.setEmail(String.valueOf(resultSet.getObject(6)));
            suppliers.add(supplier);
        }
        return suppliers;
    }

    public boolean add(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Supplier VALUES (?,?,?,?,?,?)", supplier.getSupId(), supplier.getDescription()
                , supplier.getName(), supplier.getAddress(), supplier.getPhoneNumber(), supplier.getEmail());
    }

    @Override
    public boolean delete(Supplier user, Supplier employee) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean delete(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Supplier WHERE Sup_Id=?", supplier.getSupId());
    }

    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Supplier SET Description=?,Name=?,Address=?,Phone_number=?,Email=? WHERE Sup_Id=?"
                , supplier.getDescription(), supplier.getName(), supplier.getAddress(), supplier.getPhoneNumber(), supplier.getEmail(), supplier.getSupId());
    }

    public String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Sup_Id FROM Supplier ORDER BY Sup_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    public ArrayList<Supplier> search(Supplier supplier) throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        String setColumn;
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(supplier.getName());
        if (matcher.matches()) {
            setColumn = "SELECT * FROM Supplier WHERE Name LIKE ?";
        } else {
            setColumn = "SELECT * FROM Supplier WHERE Sup_Id LIKE ?";
        }
        ResultSet resultSet = CrudUtil.setQuery(setColumn, "%" + supplier.getName() + "%");
        while (resultSet.next()) {
            Supplier searchSupplier = new Supplier();
            searchSupplier.setSupId(String.valueOf(resultSet.getObject(1)));
            searchSupplier.setDescription(String.valueOf(resultSet.getObject(2)));
            searchSupplier.setName(String.valueOf(resultSet.getObject(3)));
            searchSupplier.setAddress(String.valueOf(resultSet.getObject(4)));
            searchSupplier.setPhoneNumber(String.valueOf(resultSet.getObject(5)));
            searchSupplier.setEmail(String.valueOf(resultSet.getObject(6)));

            suppliers.add(searchSupplier);
        }
        return suppliers;
    }
}
