package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.ProductServiceDetail;
import lk.ijse.salongeetha.to.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProductServiceBO extends SuperBOImpl {
    boolean checkAlreadyExists(ProductServiceDetail productServiceDetail) throws SQLException, ClassNotFoundException;

    boolean addProductAndServiceDetail(ProductServiceDetail productServiceDetail) throws SQLException, ClassNotFoundException;

    ResultSet searchService(boolean value, Service service) throws SQLException, ClassNotFoundException;

    boolean deleteProductAndServiceDetail(ProductServiceDetail productServiceDetail) throws SQLException, ClassNotFoundException;

    ResultSet getAllService() throws SQLException, ClassNotFoundException;

    ResultSet getAllProduct() throws SQLException, ClassNotFoundException;

    ResultSet getAllProductAndServiceDAO() throws SQLException, ClassNotFoundException;
}
