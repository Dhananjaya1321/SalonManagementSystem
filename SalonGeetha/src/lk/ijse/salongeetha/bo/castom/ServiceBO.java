package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.dto.ServiceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ServiceBO extends SuperBOImpl {
    boolean deleteService(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException;

    String checkIdService() throws SQLException, ClassNotFoundException;

    ArrayList<ServiceDTO> searchService(boolean value, ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException;

    ArrayList<ServiceDTO> getAllService() throws SQLException, ClassNotFoundException;

    boolean addService(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException;
}
