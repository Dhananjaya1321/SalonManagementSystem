package lk.ijse.salongeetha.model;

import lk.ijse.salongeetha.db.DBConnection;
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
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("INSERT INTO Product VALUES (?,?,?,?,?,?,?)");
        preparedStatement.setObject(1, product.getProId());
        preparedStatement.setObject(2, product.getDescription());
        preparedStatement.setObject(3, product.getCategory());
        preparedStatement.setObject(4, product.getBrand());
        preparedStatement.setObject(5, product.getUnitPrice());
        preparedStatement.setObject(6, product.getQtyOnHand());
        preparedStatement.setObject(7, product.getSupId());
        int executeUpdate = preparedStatement.executeUpdate();
        if (executeUpdate > 0) {
            return true;
        }
        return false;
    }

    public static boolean deleteProduct(Product product) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("DELETE FROM Product WHERE Pro_Id=?");
        preparedStatement.setObject(1, product.getProId());
        int executeUpdate = preparedStatement.executeUpdate();
        if (executeUpdate > 0) {
            return true;
        }
        return false;
    }

    public static boolean updateProduct(Product product) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("UPDATE Product SET Description=?,Category=?,Brand=?,Unit_price=?" +
                        ",Qty_on_hand=?,Sup_Id=? WHERE Pro_Id=?");
        preparedStatement.setObject(1, product.getDescription());
        preparedStatement.setObject(2, product.getCategory());
        preparedStatement.setObject(3, product.getBrand());
        preparedStatement.setObject(4, product.getUnitPrice());
        preparedStatement.setObject(5, product.getQtyOnHand());
        preparedStatement.setObject(6, product.getSupId());
        preparedStatement.setObject(7, product.getProId());
        int executeUpdate = preparedStatement.executeUpdate();
        if (executeUpdate > 0) {
            return true;
        }
        return false;
    }

    public static ArrayList<Product> searchProduct(Product product) throws SQLException, ClassNotFoundException {
        ArrayList<Product> products=new ArrayList<>();
        String setColumn;
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(product.getBrand());
        if (matcher.matches()) {
            setColumn="SELECT * FROM Product WHERE Brand LIKE ?";
        }else {
            setColumn="SELECT * FROM Product WHERE Pro_Id LIKE ?";
        }
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection().prepareStatement(setColumn);
        prepareStatement.setObject(1,"%"+product.getBrand()+"%");
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            Product searchProduct=new Product();
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
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT Pro_Id FROM Product ORDER BY Pro_Id DESC LIMIT 1");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    public static ArrayList<Product> getAllProduct() throws SQLException, ClassNotFoundException {
        ArrayList<Product> products = new ArrayList<>();
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT * FROM Product");
        ResultSet resultSet = preparedStatement.executeQuery();

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
