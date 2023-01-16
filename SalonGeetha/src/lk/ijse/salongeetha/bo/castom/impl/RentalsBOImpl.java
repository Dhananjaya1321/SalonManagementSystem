package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.RentalsBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.RentalsDAO;
import lk.ijse.salongeetha.to.RentalsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RentalsBOImpl implements RentalsBO {
   RentalsDAO rentalsDAO= (RentalsDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.RENTALS);
    @Override
    public boolean deleteRental(RentalsDTO rentalsDTO) throws SQLException, ClassNotFoundException {
        return rentalsDAO.delete(rentalsDTO);
    }
    @Override
    public boolean addRental(RentalsDTO rentalsDTO) throws SQLException, ClassNotFoundException {
        return rentalsDAO.add(rentalsDTO);
    }
    @Override
    public boolean updateRental(RentalsDTO rentalsDTO) throws SQLException, ClassNotFoundException {
        return rentalsDAO.update(rentalsDTO);
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
    public ResultSet searchRental(boolean value, RentalsDTO rentalsDTO) throws SQLException, ClassNotFoundException {
        return rentalsDAO.search(value, rentalsDTO);
    }
}
