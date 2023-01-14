package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.to.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProductBO {
    boolean deleteProduct(Product product) throws SQLException, ClassNotFoundException;

    boolean addProduct(Product product) throws SQLException, ClassNotFoundException;

    boolean updateProduct(Product product) throws SQLException, ClassNotFoundException;

    String checkIdProduct() throws SQLException, ClassNotFoundException;

    ResultSet searchProduct(boolean value, Product product) throws SQLException, ClassNotFoundException;

    ResultSet getAllSupplier() throws SQLException, ClassNotFoundException;
}
