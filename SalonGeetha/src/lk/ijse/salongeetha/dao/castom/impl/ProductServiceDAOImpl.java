package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.ProductServiceDAO;
import lk.ijse.salongeetha.to.ProductServiceDetailDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductServiceDAOImpl implements ProductServiceDAO {
    public boolean delete(ProductServiceDetailDTO productServiceDetailDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM product_service_detail WHERE Pro_Id=? AND Sev_Id=?"
                , productServiceDetailDTO.getProId(), productServiceDetailDTO.getSevId());
    }

    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("select ps.Pro_Id,ps.Sev_Id,ps.Qty,s.Name from product_service_detail ps inner join service s on ps.Sev_Id = s.Sev_Id;");
    }


    public boolean add(ProductServiceDetailDTO productServiceDetailDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO product_service_detail VALUES (?,?,?)", productServiceDetailDTO.getProId(),
                productServiceDetailDTO.getSevId(), productServiceDetailDTO.getQty());
    }

    @Override
    public boolean checkAlreadyExists(ProductServiceDetailDTO productServiceDetailDTO) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM product_service_detail WHERE Pro_Id=? AND Sev_Id=?", productServiceDetailDTO.getProId()
                , productServiceDetailDTO.getSevId());
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(ProductServiceDetailDTO supplier) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet search(boolean value, ProductServiceDetailDTO to) throws SQLException, ClassNotFoundException {
        return null;
    }


}
