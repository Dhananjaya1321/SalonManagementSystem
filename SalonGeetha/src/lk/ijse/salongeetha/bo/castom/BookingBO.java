package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.BookDTO;
import lk.ijse.salongeetha.to.BookRentalsDetailDTO;
import lk.ijse.salongeetha.to.CustomerDTO;
import lk.ijse.salongeetha.to.RentalsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingBO extends SuperBOImpl {
    ResultSet searchRentalsDetails(RentalsDTO rental) throws SQLException, ClassNotFoundException;

    ResultSet searchCustomerDetails(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    String checkIdBooking() throws SQLException, ClassNotFoundException;

    ResultSet getAllRentals() throws SQLException, ClassNotFoundException;

    ResultSet getAllCustomer() throws SQLException, ClassNotFoundException;

    boolean addBooking(BookDTO bookDTO, ArrayList<BookRentalsDetailDTO> bookRentalsDetailDTOS) throws SQLException, ClassNotFoundException;
}
