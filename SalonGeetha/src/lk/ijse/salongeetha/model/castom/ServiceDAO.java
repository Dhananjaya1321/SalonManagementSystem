package lk.ijse.salongeetha.model.castom;

import lk.ijse.salongeetha.model.SQLUtil;
import lk.ijse.salongeetha.to.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ServiceDAO extends SQLUtil<Service> {
    ResultSet searchServiceDetails(Service service) throws SQLException, ClassNotFoundException;
}
