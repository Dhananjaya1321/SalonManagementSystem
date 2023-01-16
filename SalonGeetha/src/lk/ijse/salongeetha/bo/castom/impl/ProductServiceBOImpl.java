package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.ProductServiceBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.ProductDAO;
import lk.ijse.salongeetha.dao.castom.ProductServiceDAO;
import lk.ijse.salongeetha.dao.castom.ServiceDAO;
import lk.ijse.salongeetha.to.ProductServiceDetailDTO;
import lk.ijse.salongeetha.to.ServiceDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductServiceBOImpl implements ProductServiceBO {
    ProductDAO productDAO = (ProductDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.PRODUCT);
    ServiceDAO serviceDAO = (ServiceDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.SERVICE);
    ProductServiceDAO productServiceDAO = (ProductServiceDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.PRODUCT_SERVICE);

    @Override
    public boolean checkAlreadyExists(ProductServiceDetailDTO productServiceDetailDTO) throws SQLException, ClassNotFoundException {
        return productServiceDAO.checkAlreadyExists(productServiceDetailDTO);
    }

    @Override
    public boolean addProductAndServiceDetail(ProductServiceDetailDTO productServiceDetailDTO) throws SQLException, ClassNotFoundException {
        return productServiceDAO.add(productServiceDetailDTO);
    }

    @Override
    public ResultSet searchService(boolean value, ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException {
        return serviceDAO.search(value, serviceDTO);
    }

    @Override
    public boolean deleteProductAndServiceDetail(ProductServiceDetailDTO productServiceDetailDTO) throws SQLException, ClassNotFoundException {
        return productServiceDAO.delete(productServiceDetailDTO);
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
