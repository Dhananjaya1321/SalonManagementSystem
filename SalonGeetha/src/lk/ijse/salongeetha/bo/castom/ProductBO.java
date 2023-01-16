package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProductBO extends SuperBOImpl {
    boolean deleteProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException;

    boolean addProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException;

    boolean updateProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException;

    String checkIdProduct() throws SQLException, ClassNotFoundException;

    ResultSet searchProduct(boolean value, ProductDTO productDTO) throws SQLException, ClassNotFoundException;

    ResultSet getAllSupplier() throws SQLException, ClassNotFoundException;
    ResultSet getAllProduct() throws SQLException, ClassNotFoundException;
}
