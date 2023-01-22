package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.ProductServiceDAO;
import lk.ijse.salongeetha.entity.ProductServiceDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductServiceDAOImpl implements ProductServiceDAO {

    public boolean delete(ProductServiceDetail detail) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM product_service_detail WHERE Pro_Id=? AND Sev_Id=?"
                , detail.getProId(), detail.getSevId());
    }

    public ArrayList<ProductServiceDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }


    public boolean add(ProductServiceDetail detail) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO product_service_detail VALUES (?,?,?)", detail.getProId(),
                detail.getSevId(), detail.getQty());
    }

    @Override
    public boolean checkAlreadyExists(ProductServiceDetail detail) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM product_service_detail WHERE Pro_Id=? AND Sev_Id=?", detail.getProId()
                , detail.getSevId());
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(ProductServiceDetail detail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<ProductServiceDetail> search(boolean value, ProductServiceDetail detail) throws SQLException, ClassNotFoundException {
        return null;
    }
}
