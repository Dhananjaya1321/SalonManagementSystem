package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.to.ProductServiceDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductServiceModel {
    public static boolean deleteProductService(ProductServiceDetail productServiceDetail) throws SQLException, ClassNotFoundException {
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("DELETE FROM product_service_detail WHERE Pro_Id=? AND Sev_Id=?");
        prepareStatement.setObject(1,productServiceDetail.getProId());
        prepareStatement.setObject(2,productServiceDetail.getSevId());
        int executeUpdate = prepareStatement.executeUpdate();
        if (executeUpdate > 0) {
            return true;
        }
        return false;
    }

    public static ArrayList<ProductServiceDetail> getAllProductServiceDetails() throws SQLException, ClassNotFoundException {
        ArrayList<ProductServiceDetail> productServiceDetails = new ArrayList<>();
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("select ps.Pro_Id,ps.Sev_Id,ps.Qty,s.Name from product_service_detail ps inner join service s on ps.Sev_Id = s.Sev_Id;");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            ProductServiceDetail productServiceDetail=new ProductServiceDetail();
            productServiceDetail.setProId(String.valueOf(resultSet.getObject(1)));
            productServiceDetail.setSevId(String.valueOf(resultSet.getObject(2)));
            productServiceDetail.setQty((Integer) resultSet.getObject(3));
            productServiceDetail.setName(String.valueOf(resultSet.getObject(4)));
            productServiceDetails.add(productServiceDetail);
        }
        return productServiceDetails;
    }

    public static boolean addProductService(ProductServiceDetail productServiceDetail) throws SQLException, ClassNotFoundException {
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("INSERT INTO product_service_detail VALUES (?,?,?)");
        prepareStatement.setObject(1,productServiceDetail.getProId());
        prepareStatement.setObject(2,productServiceDetail.getSevId());
        prepareStatement.setObject(3,productServiceDetail.getQty());
        boolean isAdded = prepareStatement.executeUpdate() > 0;
        if (isAdded) {
            return true;
        }
        return false;
    }

    public static boolean checkAlreadyExists(ProductServiceDetail productServiceDetail) throws SQLException, ClassNotFoundException {
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT * FROM product_service_detail WHERE Pro_Id=? AND Sev_Id=?");
        prepareStatement.setObject(1,productServiceDetail.getProId());
        prepareStatement.setObject(2,productServiceDetail.getSevId());
        ResultSet resultSet = prepareStatement.executeQuery();
        if (resultSet.next()) {
            return true;
        }
        return false;
    }
}
