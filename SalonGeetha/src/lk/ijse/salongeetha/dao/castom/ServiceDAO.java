package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.entity.Service;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ServiceDAO extends SQLUtil<Service> {
    ArrayList<Service> searchServiceDetails(Service service) throws SQLException, ClassNotFoundException;
}
