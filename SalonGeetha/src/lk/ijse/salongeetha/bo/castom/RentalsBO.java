package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.Rentals;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RentalsBO extends SuperBOImpl {
    boolean deleteRental(Rentals rentals) throws SQLException, ClassNotFoundException;

    boolean addRental(Rentals rentals) throws SQLException, ClassNotFoundException;

    boolean updateRental(Rentals rentals) throws SQLException, ClassNotFoundException;

    String checkIdRental() throws SQLException, ClassNotFoundException;

    ResultSet getAllRental() throws SQLException, ClassNotFoundException;

    ResultSet searchRental(boolean value, Rentals rentals) throws SQLException, ClassNotFoundException;
}
