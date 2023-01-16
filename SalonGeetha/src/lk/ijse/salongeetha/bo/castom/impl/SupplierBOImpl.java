package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.SupplierBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.SupplierDAO;
import lk.ijse.salongeetha.to.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO= (SupplierDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.SUPPLIER);
    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(supplierDTO);
    }
    @Override
    public ResultSet searchSupplier(Boolean value, SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.search(value, supplierDTO);
    }
    @Override
    public boolean deleteSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(supplierDTO);
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
    public boolean addSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.add(supplierDTO);
    }
}
