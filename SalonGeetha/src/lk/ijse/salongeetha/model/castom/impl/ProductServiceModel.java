package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.model.castom.ProductServiceDAO;
import lk.ijse.salongeetha.to.ProductServiceDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductServiceModel implements ProductServiceDAO {
    public boolean delete(ProductServiceDetail productServiceDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM product_service_detail WHERE Pro_Id=? AND Sev_Id=?"
                , productServiceDetail.getProId(), productServiceDetail.getSevId());
    }

    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("select ps.Pro_Id,ps.Sev_Id,ps.Qty,s.Name from product_service_detail ps inner join service s on ps.Sev_Id = s.Sev_Id;");
    }


    public boolean add(ProductServiceDetail productServiceDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO product_service_detail VALUES (?,?,?)", productServiceDetail.getProId(),
                productServiceDetail.getSevId(), productServiceDetail.getQty());
    }

    @Override
    public boolean checkAlreadyExists(ProductServiceDetail productServiceDetail) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM product_service_detail WHERE Pro_Id=? AND Sev_Id=?", productServiceDetail.getProId()
                , productServiceDetail.getSevId());
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(ProductServiceDetail supplier) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet search(boolean value, ProductServiceDetail to) throws SQLException, ClassNotFoundException {
        return null;
    }


}
