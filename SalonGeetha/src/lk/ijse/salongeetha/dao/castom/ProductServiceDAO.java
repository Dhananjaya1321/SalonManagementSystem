package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.ProductServiceDetailDTO;

import java.sql.SQLException;

public interface ProductServiceDAO extends SQLUtil<ProductServiceDetailDTO> {
    boolean checkAlreadyExists(ProductServiceDetailDTO productServiceDetailDTO) throws SQLException, ClassNotFoundException;
}
