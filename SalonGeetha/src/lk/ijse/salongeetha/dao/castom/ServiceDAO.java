package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ServiceDAO extends SQLUtil<Service> {
    ResultSet searchServiceDetails(Service service) throws SQLException, ClassNotFoundException;
}
