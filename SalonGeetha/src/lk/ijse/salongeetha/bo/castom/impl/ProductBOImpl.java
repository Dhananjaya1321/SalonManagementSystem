package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.ProductBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.ProductDAO;
import lk.ijse.salongeetha.dao.castom.SupplierDAO;
import lk.ijse.salongeetha.to.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductBOImpl implements ProductBO {
    SupplierDAO supplierDAO = (SupplierDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.SUPPLIER);
    ProductDAO productDAO = (ProductDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.PRODUCT);

    @Override
    public boolean deleteProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.delete(productDTO);
    }

    @Override
    public boolean addProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.add(productDTO);
    }

    @Override
    public boolean updateProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.update(productDTO);
    }

    @Override
    public String checkIdProduct() throws SQLException, ClassNotFoundException {
        return productDAO.checkId();
    }

    @Override
    public ResultSet searchProduct(boolean value, ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.search(value, productDTO);
    }

    @Override
    public ResultSet getAllSupplier() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAll();
    }

    @Override
    public ResultSet getAllProduct() throws SQLException, ClassNotFoundException {
        return productDAO.getAll();
    }
}
