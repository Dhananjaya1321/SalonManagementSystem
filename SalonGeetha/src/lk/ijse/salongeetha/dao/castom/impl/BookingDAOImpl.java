package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.BookingDAO;
import lk.ijse.salongeetha.to.BookDTO;
import lk.ijse.salongeetha.to.BookRentalsDetailDTO;
import lk.ijse.salongeetha.to.PaymentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDAOImpl implements BookingDAO {

    public boolean delete(BookDTO bookDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Book WHERE Bok_Id=?", bookDTO.getBokId());
    }


    public boolean update(BookDTO bookDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Book SET Status=? WHERE Bok_Id=?", bookDTO.getStatus(), bookDTO.getBokId());
    }

    public String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Bok_Id FROM book ORDER BY Bok_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    @Override
    public ResultSet search(boolean value, BookDTO to) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean addBooking(BookDTO bookDTO, ArrayList<BookRentalsDetailDTO> bookRentalsDetailDTOS) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Book (Bok_Id,Date,NIC) VALUES (?,?,?)", bookDTO.getBokId(), bookDTO.getDate(), bookDTO.getNic());
    }


    public ArrayList<BookDTO> getIdS() throws SQLException, ClassNotFoundException {
        ArrayList<BookDTO> bookDTOS = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT Bok_Id FROM Book WHERE Status='Pending'");
        while (resultSet.next()) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setBokId(String.valueOf(resultSet.getObject(1)));
            bookDTOS.add(bookDTO);
        }
        return bookDTOS;
    }

    public void getId(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("select nic from book where bok_id=?", paymentDTO.getaOrBId());
        if (resultSet.next()) {
            paymentDTO.setNic(String.valueOf(resultSet.getObject(1)));
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
    public boolean add(BookDTO user) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

}
