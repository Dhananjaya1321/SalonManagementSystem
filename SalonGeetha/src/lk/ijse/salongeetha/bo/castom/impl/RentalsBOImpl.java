package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.RentalsBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.RentalsDAO;
import lk.ijse.salongeetha.entity.Rentals;
import lk.ijse.salongeetha.dto.RentalsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class RentalsBOImpl implements RentalsBO {
    RentalsDAO rentalsDAO = (RentalsDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.RENTALS);
    private ArrayList<Rentals> rentals;
    private ArrayList<RentalsDTO> rentalsDTOS;

    @Override
    public boolean deleteRental(RentalsDTO rentalsDTO) throws SQLException, ClassNotFoundException {
        return rentalsDAO.delete(new Rentals(rentalsDTO.getRntId(), rentalsDTO.getName(),
                rentalsDTO.getDescription(), rentalsDTO.getAvaliableCount(), rentalsDTO.getPricePreDay(),
                rentalsDTO.getDiscount()));
    }

    @Override
    public boolean addRental(RentalsDTO rentalsDTO) throws SQLException, ClassNotFoundException {
        return rentalsDAO.add(new Rentals(rentalsDTO.getRntId(), rentalsDTO.getName(),
                rentalsDTO.getDescription(), rentalsDTO.getAvaliableCount(), rentalsDTO.getPricePreDay(),
                rentalsDTO.getDiscount()));
    }

    @Override
    public boolean updateRental(RentalsDTO rentalsDTO) throws SQLException, ClassNotFoundException {
        return rentalsDAO.update(new Rentals(rentalsDTO.getRntId(), rentalsDTO.getName(),
                rentalsDTO.getDescription(), rentalsDTO.getAvaliableCount(), rentalsDTO.getPricePreDay(),
                rentalsDTO.getDiscount()));
    }

    @Override
    public String checkIdRental() throws SQLException, ClassNotFoundException {
        return rentalsDAO.checkId();
    }

    @Override
    public ArrayList<RentalsDTO> getAllRental() throws SQLException, ClassNotFoundException {
        /*ArrayList<Rentals>*/
        rentals = rentalsDAO.getAll();
        /*ArrayList<RentalsDTO>*/
        rentalsDTOS = new ArrayList<>();
        for (Rentals r : rentals) {
            rentalsDTOS.add(new RentalsDTO(r.getRntId(), r.getName(), r.getDescription(), r.getAvaliableCount(),
                    r.getPricePreDay(), r.getDiscount()));
        }
        return rentalsDTOS;
    }

    @Override
    public ArrayList<RentalsDTO> searchRental(boolean value, RentalsDTO rentalsDTO) throws SQLException, ClassNotFoundException {
        /*ArrayList<Rentals>*/
        rentals = rentalsDAO.search(value, new Rentals(rentalsDTO.getRntId(), rentalsDTO.getName(),
                rentalsDTO.getDescription(), rentalsDTO.getAvaliableCount(), rentalsDTO.getPricePreDay(),
                rentalsDTO.getDiscount()));
        /*ArrayList<RentalsDTO>*/
        rentalsDTOS = new ArrayList<>();
        for (Rentals r : rentals) {
            rentalsDTOS.add(new RentalsDTO(r.getRntId(), r.getName(), r.getDescription(), r.getAvaliableCount(),
                    r.getPricePreDay(), r.getDiscount()));
        }
        return rentalsDTOS;
    }
}
