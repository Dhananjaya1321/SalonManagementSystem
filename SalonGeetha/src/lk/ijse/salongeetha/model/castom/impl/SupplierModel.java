package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.model.castom.SupplierDAO;
import lk.ijse.salongeetha.to.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierModel implements SupplierDAO {
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM Supplier");
    }

    public boolean add(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Supplier VALUES (?,?,?,?,?,?)", supplier.getSupId(), supplier.getDescription()
                , supplier.getName(), supplier.getAddress(), supplier.getPhoneNumber(), supplier.getEmail());
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

    public ResultSet search(boolean value,Supplier to) throws SQLException, ClassNotFoundException {
        String setColumn;
        if (value) {
            setColumn = "SELECT * FROM Supplier WHERE Name LIKE ?";
        } else {
            setColumn = "SELECT * FROM Supplier WHERE Sup_Id LIKE ?";
        }
        return CrudUtil.setQuery(setColumn, "%" + to.getName() + "%");
    }
}
