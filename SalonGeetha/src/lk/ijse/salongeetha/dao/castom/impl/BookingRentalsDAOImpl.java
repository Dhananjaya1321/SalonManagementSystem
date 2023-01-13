package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.BookingRentalsDAO;
import lk.ijse.salongeetha.to.BookRentalsDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingRentalsDAOImpl implements BookingRentalsDAO {
    @Override
    public boolean addDetails(ArrayList<BookRentalsDetail> bookRentalsDetails) throws SQLException, ClassNotFoundException {
        int i = 0;
        for (BookRentalsDetail b : bookRentalsDetails) {
            boolean isAdded = CrudUtil.setQuery("INSERT INTO book_rentals_detail VALUES (?,?,?,?)", b.getRentId(), b.getBokId()
                    , b.getQty(), b.getForHowManyDays());
            if (isAdded) {
                i++;
            }
        }
        if (i == bookRentalsDetails.size()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean add(BookRentalsDetail to) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(BookRentalsDetail to) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(BookRentalsDetail to) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet search(boolean value, BookRentalsDetail to) throws SQLException, ClassNotFoundException {
        return null;
    }
}
