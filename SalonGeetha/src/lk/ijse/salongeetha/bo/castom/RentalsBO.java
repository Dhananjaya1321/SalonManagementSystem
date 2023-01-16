package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.RentalsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RentalsBO extends SuperBOImpl {
    boolean deleteRental(RentalsDTO rentalsDTO) throws SQLException, ClassNotFoundException;

    boolean addRental(RentalsDTO rentalsDTO) throws SQLException, ClassNotFoundException;

    boolean updateRental(RentalsDTO rentalsDTO) throws SQLException, ClassNotFoundException;

    String checkIdRental() throws SQLException, ClassNotFoundException;

    ResultSet getAllRental() throws SQLException, ClassNotFoundException;

    ResultSet searchRental(boolean value, RentalsDTO rentalsDTO) throws SQLException, ClassNotFoundException;
}
