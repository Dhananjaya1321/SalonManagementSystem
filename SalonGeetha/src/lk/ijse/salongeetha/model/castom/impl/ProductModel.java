package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.to.Customer;
import lk.ijse.salongeetha.to.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductModel {
    public static boolean addProduct(Product product) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Product VALUES (?,?,?,?,?,?,?)", product.getProId()
                , product.getDescription(), product.getCategory(), product.getBrand(), product.getUnitPrice(), product.getQtyOnHand(), product.getSupId());
    }

    public static boolean deleteProduct(Product product) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Product WHERE Pro_Id=?", product.getProId());
    }

    public static boolean updateProduct(Product product) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Product SET Description=?,Category=?,Brand=?,Unit_price=?,Qty_on_hand=?,Sup_Id=?" +
                " WHERE Pro_Id=?", product.getDescription(), product.getCategory(), product.getBrand(), product.getUnitPrice(), product.getQtyOnHand(), product.getSupId(), product.getProId());
    }

    public static ArrayList<Product> searchProduct(Product product) throws SQLException, ClassNotFoundException {
        ArrayList<Product> products = new ArrayList<>();
        String setColumn;
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(product.getBrand());
        if (matcher.matches()) {
            setColumn = "SELECT * FROM Product WHERE Brand LIKE ?";
        } else {
            setColumn = "SELECT * FROM Product WHERE Pro_Id LIKE ?";
        }
        ResultSet resultSet = CrudUtil.setQuery(setColumn, "%" + product.getBrand() + "%");
        while (resultSet.next()) {
            Product searchProduct = new Product();
            searchProduct.setProId(String.valueOf(resultSet.getObject(1)));
            searchProduct.setDescription(String.valueOf(resultSet.getObject(2)));
            searchProduct.setCategory(String.valueOf(resultSet.getObject(3)));
            searchProduct.setBrand(String.valueOf(resultSet.getObject(4)));
            searchProduct.setUnitPrice((Double) resultSet.getObject(5));
            searchProduct.setQtyOnHand((Integer) resultSet.getObject(6));
            searchProduct.setSupId(String.valueOf(resultSet.getObject(7)));
            products.add(searchProduct);
        }
        return products;
    }

    public static String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Pro_Id FROM Product ORDER BY Pro_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    public static ArrayList<Product> getAllProduct() throws SQLException, ClassNotFoundException {
        ArrayList<Product> products = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM Product");
        while (resultSet.next()) {
            Product product = new Product();
            product.setProId(String.valueOf(resultSet.getObject(1)));
            product.setDescription(String.valueOf(resultSet.getObject(2)));
            product.setCategory(String.valueOf(resultSet.getObject(3)));
            product.setBrand(String.valueOf(resultSet.getObject(4)));
            product.setUnitPrice((Double) resultSet.getObject(5));
            product.setQtyOnHand((Integer) resultSet.getObject(6));
            product.setSupId(String.valueOf(resultSet.getObject(7)));
            products.add(product);
        }
        return products;
    }
}