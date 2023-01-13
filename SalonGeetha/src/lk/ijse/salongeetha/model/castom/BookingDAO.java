package lk.ijse.salongeetha.model.castom;

import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.model.SQLUtil;
import lk.ijse.salongeetha.to.Book;
import lk.ijse.salongeetha.to.BookRentalsDetail;
import lk.ijse.salongeetha.to.Payment;
import lk.ijse.salongeetha.to.tm.BookTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingDAO extends SQLUtil<Book> {
    boolean addBooking(Book book, ArrayList<BookRentalsDetail> bookRentalsDetails) throws SQLException, ClassNotFoundException;

    ArrayList<Book> getIdS() throws SQLException, ClassNotFoundException;

    void getId(Payment payment) throws SQLException, ClassNotFoundException;

    String getBookingCount(String setDate) throws SQLException, ClassNotFoundException;

    ArrayList<BookTM> getBookingForChart(String time) throws SQLException, ClassNotFoundException;
}
