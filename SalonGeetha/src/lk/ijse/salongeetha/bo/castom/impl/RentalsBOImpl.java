package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.RentalsBO;
import lk.ijse.salongeetha.dao.castom.RentalsDAO;
import lk.ijse.salongeetha.dao.castom.impl.RentalsDAOImpl;
import lk.ijse.salongeetha.to.Rentals;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RentalsBOImpl implements RentalsBO {
   RentalsDAO rentalsDAO=new RentalsDAOImpl();
    @Override
    public boolean deleteRental(Rentals rentals) throws SQLException, ClassNotFoundException {
        return rentalsDAO.delete(rentals);
    }
    @Override
    public boolean addRental(Rentals rentals) throws SQLException, ClassNotFoundException {
        return rentalsDAO.add(rentals);
    }
    @Override
    public boolean updateRental(Rentals rentals) throws SQLException, ClassNotFoundException {
        return rentalsDAO.update(rentals);
    }
    @Override
    public String checkIdRental() throws SQLException, ClassNotFoundException {
        return rentalsDAO.checkId();
    }
    @Override
    public ResultSet getAllRental() throws SQLException, ClassNotFoundException {
        return rentalsDAO.getAll();
    }
    @Override
    public ResultSet searchRental(boolean value, Rentals rentals) throws SQLException, ClassNotFoundException {
        return rentalsDAO.search(value,rentals);
    }
}
