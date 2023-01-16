package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.ServiceDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ServiceBO extends SuperBOImpl {
    boolean deleteService(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException;

    String checkIdService() throws SQLException, ClassNotFoundException;

    ResultSet searchService(boolean value, ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException;

    ResultSet getAllService() throws SQLException, ClassNotFoundException;

    boolean addService(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException;
}
