package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.dto.ProductDTO;
import lk.ijse.salongeetha.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBO extends SuperBOImpl {
    boolean deleteProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException;

    boolean addProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException;

    boolean updateProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException;

    String checkIdProduct() throws SQLException, ClassNotFoundException;

    ArrayList<ProductDTO> searchProduct(boolean value, ProductDTO productDTO) throws SQLException, ClassNotFoundException;

    ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException;
    ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException;
}
