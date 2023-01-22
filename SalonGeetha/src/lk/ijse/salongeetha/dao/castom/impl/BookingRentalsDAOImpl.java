package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.BookingRentalsDAO;
import lk.ijse.salongeetha.entity.BookRentalsDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookingRentalsDAOImpl implements BookingRentalsDAO {
    @Override
    public boolean addDetails(ArrayList<BookRentalsDetail> details) throws SQLException, ClassNotFoundException {
        int i = 0;
        for (BookRentalsDetail b : details) {
            boolean isAdded = CrudUtil.setQuery("INSERT INTO book_rentals_detail VALUES (?,?,?,?)", b.getRentId(), b.getBokId()
                    , b.getQty(), b.getForHowManyDays());
            if (isAdded) {
                i++;
            }
        }
        if (i == details.size()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean add(BookRentalsDetail detail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(BookRentalsDetail detail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<BookRentalsDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(BookRentalsDetail detail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<BookRentalsDetail> search(boolean value, BookRentalsDetail detail) throws SQLException, ClassNotFoundException {
        return null;
    }
}
