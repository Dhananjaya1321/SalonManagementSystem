package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.BookingBO;
import lk.ijse.salongeetha.dao.castom.BookingDAO;
import lk.ijse.salongeetha.dao.castom.BookingRentalsDAO;
import lk.ijse.salongeetha.dao.castom.CustomerDAO;
import lk.ijse.salongeetha.dao.castom.RentalsDAO;
import lk.ijse.salongeetha.dao.castom.impl.BookingDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.BookingRentalsDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.CustomerDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.RentalsDAOImpl;
import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.to.Book;
import lk.ijse.salongeetha.to.BookRentalsDetail;
import lk.ijse.salongeetha.to.Customer;
import lk.ijse.salongeetha.to.Rentals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingBOImpl implements BookingBO {
    RentalsDAO rentalsDAO = new RentalsDAOImpl();
    CustomerDAO customerDAO = new CustomerDAOImpl();
    BookingDAO bookingDAO = new BookingDAOImpl();
    BookingRentalsDAO bookingRentalsDAO = new BookingRentalsDAOImpl();

    @Override
    public ResultSet searchRentalsDetails(Rentals rental) throws SQLException, ClassNotFoundException {
        return rentalsDAO.searchRentalsDetails(rental);
    }

    @Override
    public ResultSet searchCustomerDetails(Customer customer) throws SQLException, ClassNotFoundException {
        return customerDAO.searchCustomerDetails(customer);
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
    public boolean addBooking(Book book, ArrayList<BookRentalsDetail> bookRentalsDetails) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            boolean isAdded = bookingDAO.addBooking(book, bookRentalsDetails);
            if (isAdded) {
                boolean addDetails = bookingRentalsDAO.addDetails(bookRentalsDetails);
                if (addDetails) {
                    RentalsDAO rentalsDAO = new RentalsDAOImpl();
                    boolean updateRentalQty = rentalsDAO.update(bookRentalsDetails);
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
