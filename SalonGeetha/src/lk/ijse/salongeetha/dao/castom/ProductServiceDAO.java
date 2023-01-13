package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.ProductServiceDetail;

import java.sql.SQLException;

public interface ProductServiceDAO extends SQLUtil<ProductServiceDetail> {
    boolean checkAlreadyExists(ProductServiceDetail productServiceDetail) throws SQLException, ClassNotFoundException;
}
