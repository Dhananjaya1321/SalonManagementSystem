package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.SupplierDAO;
import lk.ijse.salongeetha.to.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDAOImpl implements SupplierDAO {
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM supplier");
    }

    public boolean add(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO supplier VALUES (?,?,?,?,?,?)", supplierDTO.getSupId(), supplierDTO.getDescription()
                , supplierDTO.getName(), supplierDTO.getAddress(), supplierDTO.getPhoneNumber(), supplierDTO.getEmail());
    }


    public boolean delete(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM supplier WHERE Sup_Id=?", supplierDTO.getSupId());
    }

    public boolean update(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE supplier SET Description=?,Name=?,Address=?,Phone_number=?,Email=? WHERE Sup_Id=?"
                , supplierDTO.getDescription(), supplierDTO.getName(), supplierDTO.getAddress(), supplierDTO.getPhoneNumber(), supplierDTO.getEmail(), supplierDTO.getSupId());
    }

    public String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Sup_Id FROM supplier ORDER BY Sup_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    public ResultSet search(boolean value, SupplierDTO to) throws SQLException, ClassNotFoundException {
        String setColumn;
        if (value) {
            setColumn = "SELECT * FROM supplier WHERE Name LIKE ?";
        } else {
            setColumn = "SELECT * FROM supplier WHERE Sup_Id LIKE ?";
        }
        return CrudUtil.setQuery(setColumn, "%" + to.getName() + "%");
    }
}
