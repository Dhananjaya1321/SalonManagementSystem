package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.ProductServiceBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.ProductDAO;
import lk.ijse.salongeetha.dao.castom.ProductServiceDAO;
import lk.ijse.salongeetha.dao.castom.ServiceDAO;
import lk.ijse.salongeetha.dao.castom.impl.ProductDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.ProductServiceDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.ServiceDAOImpl;
import lk.ijse.salongeetha.to.ProductServiceDetail;
import lk.ijse.salongeetha.to.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductServiceBOImpl implements ProductServiceBO {
    ProductDAO productDAO = (ProductDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.PRODUCT);
    ServiceDAO serviceDAO = (ServiceDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.SERVICE);
    ProductServiceDAO productServiceDAO = (ProductServiceDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.PRODUCT_SERVICE);

    @Override
    public boolean checkAlreadyExists(ProductServiceDetail productServiceDetail) throws SQLException, ClassNotFoundException {
        return productServiceDAO.checkAlreadyExists(productServiceDetail);
    }

    @Override
    public boolean addProductAndServiceDetail(ProductServiceDetail productServiceDetail) throws SQLException, ClassNotFoundException {
        return productServiceDAO.add(productServiceDetail);
    }

    @Override
    public ResultSet searchService(boolean value, Service service) throws SQLException, ClassNotFoundException {
        return serviceDAO.search(value, service);
    }

    @Override
    public boolean deleteProductAndServiceDetail(ProductServiceDetail productServiceDetail) throws SQLException, ClassNotFoundException {
        return productServiceDAO.delete(productServiceDetail);
    }

    @Override
    public ResultSet getAllService() throws SQLException, ClassNotFoundException {
        return serviceDAO.getAll();
    }

    @Override
    public ResultSet getAllProduct() throws SQLException, ClassNotFoundException {
        return productDAO.getAll();
    }

    @Override
    public ResultSet getAllProductAndServiceDAO() throws SQLException, ClassNotFoundException {
        return productServiceDAO.getAll();
    }
}
