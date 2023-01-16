package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.BookingBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.BookingDAO;
import lk.ijse.salongeetha.dao.castom.BookingRentalsDAO;
import lk.ijse.salongeetha.dao.castom.CustomerDAO;
import lk.ijse.salongeetha.dao.castom.RentalsDAO;
import lk.ijse.salongeetha.dao.castom.impl.RentalsDAOImpl;
import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.to.BookDTO;
import lk.ijse.salongeetha.to.BookRentalsDetailDTO;
import lk.ijse.salongeetha.to.CustomerDTO;
import lk.ijse.salongeetha.to.RentalsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingBOImpl implements BookingBO {
    RentalsDAO rentalsDAO = (RentalsDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.RENTALS);
    CustomerDAO customerDAO = (CustomerDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.CUSTOMER);
    BookingDAO bookingDAO = (BookingDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.BOOKING);
    BookingRentalsDAO bookingRentalsDAO = (BookingRentalsDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.BOOING_RENTALS);

    @Override
    public ResultSet searchRentalsDetails(RentalsDTO rental) throws SQLException, ClassNotFoundException {
        return rentalsDAO.searchRentalsDetails(rental);
    }

    @Override
    public ResultSet searchCustomerDetails(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.searchCustomerDetails(customerDTO);
    }

    @Override
    public String checkIdBooking() throws SQLException, ClassNotFoundException {
        return bookingDAO.checkId();
    }

    @Override
    public ResultSet getAllRentals() throws SQLException, ClassNotFoundException {
        return rentalsDAO.getAll();
    }

    @Override
    public ResultSet getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    @Override
    public boolean addBooking(BookDTO bookDTO, ArrayList<BookRentalsDetailDTO> bookRentalsDetailDTOS) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            boolean isAdded = bookingDAO.addBooking(bookDTO, bookRentalsDetailDTOS);
            if (isAdded) {
                boolean addDetails = bookingRentalsDAO.addDetails(bookRentalsDetailDTOS);
                if (addDetails) {
                    RentalsDAO rentalsDAO = new RentalsDAOImpl();
                    boolean updateRentalQty = rentalsDAO.update(bookRentalsDetailDTOS);
                    if (updateRentalQty) {
                        {
                            DBConnection.getDBConnection().getConnection().commit();
                            return true;
                        }
                    }
                }
            }
            DBConnection.getDBConnection().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getDBConnection().getConnection().setAutoCommit(true);
        }
    }
}
