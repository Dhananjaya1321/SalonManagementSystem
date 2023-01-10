package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.to.Book;
import lk.ijse.salongeetha.to.BookRentalsDetail;
import lk.ijse.salongeetha.to.Payment;
import lk.ijse.salongeetha.to.tm.BookTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingModel {
    public static boolean addBooking(Book book, ArrayList<BookRentalsDetail> bookRentalsDetails) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            boolean isAdded = CrudUtil.setQuery("INSERT INTO Book (Bok_Id,Date,NIC) VALUES (?,?,?)", book.getBokId(), book.getDate(), book.getNic());
            if (isAdded) {
                boolean addDetails = BookingRentalsModel.addDetails(bookRentalsDetails);
                if (addDetails) {
                    boolean updateRentalQty = RentalsModel.updateRentalQty(bookRentalsDetails);
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

    public static boolean deleteBooking(Book book) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Book WHERE Bok_Id=?",book.getBokId());
    }

    public static boolean updateBooking(Book book) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Book SET Status=? WHERE Bok_Id=?", book.getStatus(), book.getBokId());
    }

    public static String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Bok_Id FROM book ORDER BY Bok_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    public static ArrayList<Book> getIdS() throws SQLException, ClassNotFoundException {
        ArrayList<Book> books = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT Bok_Id FROM Book WHERE Status='Pending'");
        while (resultSet.next()) {
            Book book = new Book();
            book.setBokId(String.valueOf(resultSet.getObject(1)));
            books.add(book);
        }
        return books;
    }

    public static void getId(Payment payment) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("select nic from book where bok_id=?", payment.getaOrBId());
        if (resultSet.next()) {
            payment.setNic(String.valueOf(resultSet.getObject(1)));
        }
    }

    public static String getBookingCount(String setDate) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT COUNT(Bok_Id) FROM Book WHERE Date=?",setDate);
        if (resultSet.next()) {
            String count = resultSet.getString(1);
            return count;
        }
        return null;
    }

    public static ArrayList<BookTM> getBookingForChart(String time) throws SQLException, ClassNotFoundException {
        ArrayList<BookTM> bookTMS = new ArrayList<>();
        String quary;
        if (time.equals("Past 7 day")) {
            quary = "SELECT COUNT(Bok_Id), Date FROM Book GROUP BY Date ORDER BY Date ASC LIMIT 7";
        } else if (time.equals("Past 30 day")) {
            quary = "SELECT COUNT(Bok_Id), Date FROM Book GROUP BY Date ORDER BY Date ASC LIMIT 30";
        } else if (time.equals("Past 1 year")) {
            quary = "SELECT COUNT(Bok_Id), Date FROM Book GROUP BY Date ORDER BY Date ASC LIMIT 365";
        } else {
            quary = "SELECT COUNT(Bok_Id), Date FROM Book GROUP BY Date";
        }

        ResultSet resultSet = CrudUtil.setQuery(quary);
        while (resultSet.next()) {
            BookTM bookTM = new BookTM();
            bookTM.setQty(resultSet.getInt(1));
            bookTM.setDate(resultSet.getString(2));
            bookTMS.add(bookTM);
        }
        return bookTMS;
    }
}