package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.SupplierBO;
import lk.ijse.salongeetha.dao.castom.SupplierDAO;
import lk.ijse.salongeetha.dao.castom.impl.SupplierDAOImpl;
import lk.ijse.salongeetha.to.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO=new SupplierDAOImpl();
    @Override
    public boolean updateSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(supplier);
    }
    @Override
    public ResultSet searchSupplier(Boolean value, Supplier supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.search(value,supplier);
    }
    @Override
    public boolean deleteSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(supplier);
    }
    @Override
    public String checkIdSupplier() throws SQLException, ClassNotFoundException {
        return supplierDAO.checkId();
    }
    @Override
    public ResultSet getAllSupplier() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAll();
    }
    @Override
    public boolean addSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.add(supplier);
    }
}
