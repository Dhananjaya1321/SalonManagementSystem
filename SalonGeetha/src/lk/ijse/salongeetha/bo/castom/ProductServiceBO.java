package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.ProductServiceDetailDTO;
import lk.ijse.salongeetha.to.ServiceDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProductServiceBO extends SuperBOImpl {
    boolean checkAlreadyExists(ProductServiceDetailDTO productServiceDetailDTO) throws SQLException, ClassNotFoundException;

    boolean addProductAndServiceDetail(ProductServiceDetailDTO productServiceDetailDTO) throws SQLException, ClassNotFoundException;

    ResultSet searchService(boolean value, ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException;

    boolean deleteProductAndServiceDetail(ProductServiceDetailDTO productServiceDetailDTO) throws SQLException, ClassNotFoundException;

    ResultSet getAllService() throws SQLException, ClassNotFoundException;

    ResultSet getAllProduct() throws SQLException, ClassNotFoundException;

    ResultSet getAllProductAndServiceDAO() throws SQLException, ClassNotFoundException;
}
