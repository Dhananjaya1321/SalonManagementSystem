package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.ProductDAO;
import lk.ijse.salongeetha.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO {
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

    public String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Pro_Id FROM Product ORDER BY Pro_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<Product> search(boolean value, Product product) throws SQLException, ClassNotFoundException {
        String setColumn;
        if (value) {
            setColumn = "SELECT * FROM Product WHERE Brand LIKE ?";
        } else {
            setColumn = "SELECT * FROM Product WHERE Pro_Id LIKE ?";
        }
        ArrayList<Product> arrayList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery(setColumn, "%" + product.getBrand() + "%");
        while (resultSet.next()) {
            arrayList.add(
                    new Product(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(7),
                            resultSet.getDouble(5),
                            resultSet.getInt(6)
                    )
            );
        }
        return arrayList;
    }

    public ArrayList<Product> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Product> arrayList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM Product");
        while (resultSet.next()) {
            arrayList.add(
                    new Product(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(7),
                            resultSet.getDouble(5),
                            resultSet.getInt(6)
                    )
            );
        }
        return arrayList;
    }
}
