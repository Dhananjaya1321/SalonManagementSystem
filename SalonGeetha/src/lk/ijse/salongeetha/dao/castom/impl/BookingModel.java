package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.BookingDAO;
import lk.ijse.salongeetha.dao.castom.BookingRentalsDAO;
import lk.ijse.salongeetha.dao.castom.RentalsDAO;
import lk.ijse.salongeetha.to.Book;
import lk.ijse.salongeetha.to.BookRentalsDetail;
import lk.ijse.salongeetha.to.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingModel implements BookingDAO {

    public boolean delete(Book book) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Book WHERE Bok_Id=?",book.getBokId());
    }


    public boolean update(Book book) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Book SET Status=? WHERE Bok_Id=?", book.getStatus(), book.getBokId());
    }

    public String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Bok_Id FROM book ORDER BY Bok_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    @Override
    public ResultSet search(boolean value, Book to) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean addBooking(Book book, ArrayList<BookRentalsDetail> bookRentalsDetails) throws SQLException, ClassNotFoundException {
        BookingRentalsDAO bookingRentalsDAO=new BookingRentalsModel();
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            boolean isAdded = CrudUtil.setQuery("INSERT INTO Book (Bok_Id,Date,NIC) VALUES (?,?,?)", book.getBokId(), book.getDate(), book.getNic());
            if (isAdded) {
                boolean addDetails = bookingRentalsDAO.addDetails(bookRentalsDetails);
                if (addDetails) {
                    RentalsDAO rentalsDAO=new RentalsModel();
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


    public ArrayList<Book> getIdS() throws SQLException, ClassNotFoundException {
        ArrayList<Book> books = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT Bok_Id FROM Book WHERE Status='Pending'");
        while (resultSet.next()) {
            Book book = new Book();
            book.setBokId(String.valueOf(resultSet.getObject(1)));
            books.add(book);
        }
        return books;
    }

    public void getId(Payment payment) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("select nic from book where bok_id=?", payment.getaOrBId());
        if (resultSet.next()) {
            payment.setNic(String.valueOf(resultSet.getObject(1)));
        }
    }

    public String getBookingCount(String setDate) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT COUNT(Bok_Id) FROM Book WHERE Date=?",setDate);
        if (resultSet.next()) {
            String count = resultSet.getString(1);
            return count;
        }
        return null;
    }

    public ResultSet getBookingForChart(String time) throws SQLException, ClassNotFoundException {
        String query;
        if (time.equals("Past 7 day")) {
            query = "SELECT COUNT(Bok_Id), Date FROM Book GROUP BY Date ORDER BY Date ASC LIMIT 7";
        } else if (time.equals("Past 30 day")) {
            query = "SELECT COUNT(Bok_Id), Date FROM Book GROUP BY Date ORDER BY Date ASC LIMIT 30";
        } else if (time.equals("Past 1 year")) {
            query = "SELECT COUNT(Bok_Id), Date FROM Book GROUP BY Date ORDER BY Date ASC LIMIT 365";
        } else {
            query = "SELECT COUNT(Bok_Id), Date FROM Book GROUP BY Date";
        }
        return CrudUtil.setQuery(query);
    }


    @Override
    public boolean add(Book user) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

}
