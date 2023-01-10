package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.to.ProductServiceDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductServiceModel {
    public static boolean deleteProductService(ProductServiceDetail productServiceDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM product_service_detail WHERE Pro_Id=? AND Sev_Id=?"
                , productServiceDetail.getProId(), productServiceDetail.getSevId());
    }

    public static ArrayList<ProductServiceDetail> getAllProductServiceDetails() throws SQLException, ClassNotFoundException {
        ArrayList<ProductServiceDetail> productServiceDetails = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("select ps.Pro_Id,ps.Sev_Id,ps.Qty,s.Name from product_service_detail ps inner join service s on ps.Sev_Id = s.Sev_Id;");
        while (resultSet.next()) {
            ProductServiceDetail productServiceDetail = new ProductServiceDetail();
            productServiceDetail.setProId(String.valueOf(resultSet.getObject(1)));
            productServiceDetail.setSevId(String.valueOf(resultSet.getObject(2)));
            productServiceDetail.setQty((Integer) resultSet.getObject(3));
            productServiceDetail.setName(String.valueOf(resultSet.getObject(4)));
            productServiceDetails.add(productServiceDetail);
        }
        return productServiceDetails;
    }

    public static boolean addProductService(ProductServiceDetail productServiceDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO product_service_detail VALUES (?,?,?)",productServiceDetail.getProId(),
                productServiceDetail.getSevId(),productServiceDetail.getQty());
    }

    public static boolean checkAlreadyExists(ProductServiceDetail productServiceDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM product_service_detail WHERE Pro_Id=? AND Sev_Id=?",productServiceDetail.getProId()
        ,productServiceDetail.getSevId());
    }
}
