package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.ProductBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.ProductDAO;
import lk.ijse.salongeetha.dao.castom.SupplierDAO;
import lk.ijse.salongeetha.dao.castom.impl.ProductDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.SupplierDAOImpl;
import lk.ijse.salongeetha.to.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductBOImpl implements ProductBO {
    SupplierDAO supplierDAO = (SupplierDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.SUPPLIER);
    ProductDAO productDAO = (ProductDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.PRODUCT);

    @Override
    public boolean deleteProduct(Product product) throws SQLException, ClassNotFoundException {
        return productDAO.delete(product);
    }

    @Override
    public boolean addProduct(Product product) throws SQLException, ClassNotFoundException {
        return productDAO.add(product);
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException, ClassNotFoundException {
        return productDAO.update(product);
    }

    @Override
    public String checkIdProduct() throws SQLException, ClassNotFoundException {
        return productDAO.checkId();
    }

    @Override
    public ResultSet searchProduct(boolean value, Product product) throws SQLException, ClassNotFoundException {
        return productDAO.search(value, product);
    }

    @Override
    public ResultSet getAllSupplier() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAll();
    }
}
