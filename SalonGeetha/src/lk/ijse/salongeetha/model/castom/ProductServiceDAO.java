package lk.ijse.salongeetha.model.castom;

import lk.ijse.salongeetha.model.SQLUtil;
import lk.ijse.salongeetha.to.ProductServiceDetail;

import java.sql.SQLException;

public interface ProductServiceDAO extends SQLUtil<ProductServiceDetail> {
    boolean checkAlreadyExists(ProductServiceDetail productServiceDetail) throws SQLException, ClassNotFoundException;
}
