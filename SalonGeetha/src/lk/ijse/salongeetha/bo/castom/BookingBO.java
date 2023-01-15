package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.Book;
import lk.ijse.salongeetha.to.BookRentalsDetail;
import lk.ijse.salongeetha.to.Customer;
import lk.ijse.salongeetha.to.Rentals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingBO extends SuperBOImpl {
    ResultSet searchRentalsDetails(Rentals rental) throws SQLException, ClassNotFoundException;

    ResultSet searchCustomerDetails(Customer customer) throws SQLException, ClassNotFoundException;

    String checkIdBooking() throws SQLException, ClassNotFoundException;

    ResultSet getAllRentals() throws SQLException, ClassNotFoundException;

    ResultSet getAllCustomer() throws SQLException, ClassNotFoundException;

    boolean addBooking(Book book, ArrayList<BookRentalsDetail> bookRentalsDetails) throws SQLException, ClassNotFoundException;
}
