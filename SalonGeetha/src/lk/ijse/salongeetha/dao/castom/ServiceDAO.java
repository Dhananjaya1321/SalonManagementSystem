package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.ServiceDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ServiceDAO extends SQLUtil<ServiceDTO> {
    ResultSet searchServiceDetails(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException;
}
