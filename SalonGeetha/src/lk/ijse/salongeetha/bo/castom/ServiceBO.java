package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.to.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ServiceBO {
    boolean deleteService(Service service) throws SQLException, ClassNotFoundException;

    String checkIdService() throws SQLException, ClassNotFoundException;

    ResultSet searchService(boolean value, Service service) throws SQLException, ClassNotFoundException;

    ResultSet getAllService() throws SQLException, ClassNotFoundException;

    boolean addService(Service service) throws SQLException, ClassNotFoundException;
}
