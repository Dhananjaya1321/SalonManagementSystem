package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.dto.BookDTO;
import lk.ijse.salongeetha.dto.BookRentalsDetailDTO;
import lk.ijse.salongeetha.dto.CustomerDTO;
import lk.ijse.salongeetha.dto.RentalsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingBO extends SuperBOImpl {
    ArrayList<RentalsDTO> searchRentalsDetails(RentalsDTO rental) throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> searchCustomerDetails(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    String checkIdBooking() throws SQLException, ClassNotFoundException;

    ArrayList<RentalsDTO> getAllRentals() throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    boolean addBooking(BookDTO bookDTO, ArrayList<BookRentalsDetailDTO> bookRentalsDetailDTOS) throws SQLException, ClassNotFoundException;
}
