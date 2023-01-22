package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.SupplierDAO;
import lk.ijse.salongeetha.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    private ArrayList<Supplier> suppliers;

    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM supplier");
        /*ArrayList<Supplier> */
        suppliers = new ArrayList<>();
        while (resultSet.next()) {
            suppliers.add(
                    new Supplier(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return suppliers;
    }

    public boolean add(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO supplier VALUES (?,?,?,?,?,?)", supplier.getSupId(), supplier.getDescription()
                , supplier.getName(), supplier.getAddress(), supplier.getPhoneNumber(), supplier.getEmail());
    }


    public boolean delete(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM supplier WHERE Sup_Id=?", supplier.getSupId());
    }

    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE supplier SET Description=?,Name=?,Address=?,Phone_number=?,Email=? WHERE Sup_Id=?"
                , supplier.getDescription(), supplier.getName(), supplier.getAddress(), supplier.getPhoneNumber(), supplier.getEmail(), supplier.getSupId());
    }

    public String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Sup_Id FROM supplier ORDER BY Sup_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    public ArrayList<Supplier> search(boolean value, Supplier supplier) throws SQLException, ClassNotFoundException {
        String setColumn;
        if (value) {
            setColumn = "SELECT * FROM supplier WHERE Name LIKE ?";
        } else {
            setColumn = "SELECT * FROM supplier WHERE Sup_Id LIKE ?";
        }
        ResultSet resultSet = CrudUtil.setQuery(setColumn, "%" + supplier.getName() + "%");
        /*ArrayList<Supplier>*/
        suppliers = new ArrayList<>();
        while (resultSet.next()) {
            suppliers.add(
                    new Supplier(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return suppliers;
    }
}
