package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.entity.Book;
import lk.ijse.salongeetha.entity.BookPayment;
import lk.ijse.salongeetha.entity.BookRentalsDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingDAO extends SQLUtil<Book> {
    boolean addBooking(Book book, ArrayList<BookRentalsDetail> bookRentalsDetailD) throws SQLException, ClassNotFoundException;

    ArrayList<Book> getIdS() throws SQLException, ClassNotFoundException;

    String getId(BookPayment payment) throws SQLException, ClassNotFoundException;

    int getBookingCount(String setDate) throws SQLException, ClassNotFoundException;

}
