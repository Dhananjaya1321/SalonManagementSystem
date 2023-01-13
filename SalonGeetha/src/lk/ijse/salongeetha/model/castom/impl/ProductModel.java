package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.model.castom.ProductDAO;
import lk.ijse.salongeetha.to.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductModel implements ProductDAO {
    public boolean add(Product product) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Product VALUES (?,?,?,?,?,?,?)", product.getProId()
                , product.getDescription(), product.getCategory(), product.getBrand(), product.getUnitPrice(), product.getQtyOnHand(), product.getSupId());
    }

    public boolean delete(Product product) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Product WHERE Pro_Id=?", product.getProId());
    }

    public boolean update(Product product) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Product SET Description=?,Category=?,Brand=?,Unit_price=?,Qty_on_hand=?,Sup_Id=?" +
                " WHERE Pro_Id=?", product.getDescription(), product.getCategory(), product.getBrand(), product.getUnitPrice(), product.getQtyOnHand(), product.getSupId(), product.getProId());
    }

    public ArrayList<Product> search(Product product) throws SQLException, ClassNotFoundException {
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

    public String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Pro_Id FROM Product ORDER BY Pro_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    @Override
    public ResultSet search(boolean value, Product to) throws SQLException, ClassNotFoundException {
        String setColumn;
        if (value) {
            setColumn = "SELECT * FROM Product WHERE Brand LIKE ?";
        } else {
            setColumn = "SELECT * FROM Product WHERE Pro_Id LIKE ?";
        }
        return CrudUtil.setQuery(setColumn, "%" + to.getBrand() + "%");
    }

    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM Product");
    }
}
