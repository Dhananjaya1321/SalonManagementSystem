package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.BookingDAO;
import lk.ijse.salongeetha.entity.Book;
import lk.ijse.salongeetha.entity.BookPayment;
import lk.ijse.salongeetha.entity.BookRentalsDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDAOImpl implements BookingDAO {

    public boolean delete(Book book) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Book WHERE Bok_Id=?", book.getBokId());
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
    public ArrayList<Book> search(boolean value, Book book) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean addBooking(Book bookDTO, ArrayList<BookRentalsDetail> details) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Book (Bok_Id,Date,NIC) VALUES (?,?,?)", bookDTO.getBokId(), bookDTO.getDate(), bookDTO.getNic());
    }


    public ArrayList<Book> getIdS() throws SQLException, ClassNotFoundException {
        ArrayList<Book> bookDTOS = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT Bok_Id FROM Book WHERE Status='Pending'");
        while (resultSet.next()) {
            Book bookDTO = new Book();
            bookDTO.setBokId(String.valueOf(resultSet.getObject(1)));
            bookDTOS.add(bookDTO);
        }
        return bookDTOS;
    }

    public String getId(BookPayment payment) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("select nic from book where bok_id=?", payment.getBok_Id());
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public int getBookingCount(String setDate) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT COUNT(Bok_Id) FROM Book WHERE Date=?",setDate);
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return -1;
    }

    @Override
    public boolean add(Book user) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public ArrayList<Book> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

}
