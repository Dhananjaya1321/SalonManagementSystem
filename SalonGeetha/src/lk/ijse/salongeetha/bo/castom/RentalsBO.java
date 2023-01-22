package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.dto.RentalsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RentalsBO extends SuperBOImpl {
    boolean deleteRental(RentalsDTO rentalsDTO) throws SQLException, ClassNotFoundException;

    boolean addRental(RentalsDTO rentalsDTO) throws SQLException, ClassNotFoundException;

    boolean updateRental(RentalsDTO rentalsDTO) throws SQLException, ClassNotFoundException;

    String checkIdRental() throws SQLException, ClassNotFoundException;

    ArrayList<RentalsDTO> getAllRental() throws SQLException, ClassNotFoundException;

    ArrayList<RentalsDTO> searchRental(boolean value, RentalsDTO rentalsDTO) throws SQLException, ClassNotFoundException;
}
