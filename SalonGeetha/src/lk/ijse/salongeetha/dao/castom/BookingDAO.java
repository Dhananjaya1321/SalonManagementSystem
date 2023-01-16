package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.BookDTO;
import lk.ijse.salongeetha.to.BookRentalsDetailDTO;
import lk.ijse.salongeetha.to.PaymentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingDAO extends SQLUtil<BookDTO> {
    boolean addBooking(BookDTO bookDTO, ArrayList<BookRentalsDetailDTO> bookRentalsDetailDTOS) throws SQLException, ClassNotFoundException;

    ArrayList<BookDTO> getIdS() throws SQLException, ClassNotFoundException;

    void getId(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;

    String getBookingCount(String setDate) throws SQLException, ClassNotFoundException;

    ResultSet getBookingForChart(String time) throws SQLException, ClassNotFoundException;
}
