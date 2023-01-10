package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
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
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT * FROM Supplier");
        ResultSet resultSet = prepareStatement.executeQuery();
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
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("INSERT INTO Supplier VALUES (?,?,?,?,?,?)");
        prepareStatement.setObject(1, supplier.getSupId());
        prepareStatement.setObject(2, supplier.getDescription());
        prepareStatement.setObject(3, supplier.getName());
        prepareStatement.setObject(4, supplier.getAddress());
        prepareStatement.setObject(5, supplier.getPhoneNumber());
        prepareStatement.setObject(6, supplier.getEmail());
        boolean executeUpdate = prepareStatement.executeUpdate() > 0;
        if (executeUpdate) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Supplier user, Supplier employee) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean delete(Supplier supplier) throws SQLException, ClassNotFoundException {
//        searchEmployee(employee);
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("DELETE FROM Supplier WHERE Sup_Id=?");
        preparedStatement.setObject(1, supplier.getSupId());
        int executeUpdate = preparedStatement.executeUpdate();
        if (executeUpdate > 0) {
            return true;
        }
        return false;
    }

    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("UPDATE Supplier SET Description=?,Name=?,Address=?,Phone_number=?,Email=? WHERE Sup_Id=?");
        preparedStatement.setObject(1, supplier.getDescription());
        preparedStatement.setObject(2, supplier.getName());
        preparedStatement.setObject(3, supplier.getAddress());
        preparedStatement.setObject(4, supplier.getPhoneNumber());
        preparedStatement.setObject(5, supplier.getEmail());
        preparedStatement.setObject(6, supplier.getSupId());
        int executeUpdate = preparedStatement.executeUpdate();
        if (executeUpdate > 0) {
            return true;
        }
        return false;
    }

    public String checkId() throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT Sup_Id FROM Supplier ORDER BY Sup_Id DESC LIMIT 1");
        ResultSet resultSet = preparedStatement.executeQuery();
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
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection().prepareStatement(setColumn);
        prepareStatement.setObject(1, "%" + supplier.getName() + "%");
        ResultSet resultSet = prepareStatement.executeQuery();
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
