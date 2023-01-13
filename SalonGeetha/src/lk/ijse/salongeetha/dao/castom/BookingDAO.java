package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.Book;
import lk.ijse.salongeetha.to.BookRentalsDetail;
import lk.ijse.salongeetha.to.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingDAO extends SQLUtil<Book> {
    boolean addBooking(Book book, ArrayList<BookRentalsDetail> bookRentalsDetails) throws SQLException, ClassNotFoundException;

    ArrayList<Book> getIdS() throws SQLException, ClassNotFoundException;

    void getId(Payment payment) throws SQLException, ClassNotFoundException;

    String getBookingCount(String setDate) throws SQLException, ClassNotFoundException;

    ResultSet getBookingForChart(String time) throws SQLException, ClassNotFoundException;
}
