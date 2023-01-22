package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.dto.ProductDTO;
import lk.ijse.salongeetha.dto.ProductServiceDetailDTO;
import lk.ijse.salongeetha.dto.ServiceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductServiceBO extends SuperBOImpl {
    boolean checkAlreadyExists(ProductServiceDetailDTO productServiceDetailDTO) throws SQLException, ClassNotFoundException;

    boolean addProductAndServiceDetail(ProductServiceDetailDTO productServiceDetailDTO) throws SQLException, ClassNotFoundException;

    ArrayList<ServiceDTO> searchService(boolean value, ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException;

    boolean deleteProductAndServiceDetail(ProductServiceDetailDTO productServiceDetailDTO) throws SQLException, ClassNotFoundException;

    ArrayList<ServiceDTO> getAllService() throws SQLException, ClassNotFoundException;

    ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException;

    ArrayList<ProductServiceDetailDTO> getAllProductAndServiceDAO() throws SQLException, ClassNotFoundException;
}
