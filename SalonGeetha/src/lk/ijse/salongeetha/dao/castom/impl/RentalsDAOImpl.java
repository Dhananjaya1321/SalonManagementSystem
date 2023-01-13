package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.RentalsDAO;
import lk.ijse.salongeetha.to.BookRentalsDetail;
import lk.ijse.salongeetha.to.Rentals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RentalsDAOImpl implements RentalsDAO {
    public boolean add(Rentals rentals) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Rentals VALUES (?,?,?,?,?,?)", rentals.getRntId(), rentals.getName()
                , rentals.getPricePreDay(), rentals.getDescription(), rentals.getAvaliableCount(), rentals.getDiscount());
    }

    public boolean delete(Rentals rentals) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Rentals WHERE Rent_Id=?", rentals.getRntId());
    }

    public boolean update(Rentals rentals) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Rentals SET Name=?,Price_pre_day=?,Description=?,Avaliable_count=?,Discount=? " +
                "WHERE Rent_Id=?", rentals.getName(), rentals.getPricePreDay(), rentals.getDescription(), rentals.getAvaliableCount(), rentals.getDiscount(), rentals.getRntId());
    }

    public String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Rent_Id FROM Rentals ORDER BY Rent_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }


    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM Rentals");
    }

    public ResultSet search(boolean value, Rentals rental) throws SQLException, ClassNotFoundException {
        String setColumn;
        if (value) {
            setColumn = "SELECT * FROM Rentals WHERE Name LIKE ?";
        } else {
            setColumn = "SELECT * FROM Rentals WHERE Rent_Id LIKE ?";
        }
        return CrudUtil.setQuery(setColumn, "%" + rental.getName() + "%");
    }

    @Override
    public ResultSet searchRentalsDetails(Rentals rental) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM Rentals WHERE Rent_Id=?", rental.getRntId());
    }

    public boolean update(ArrayList<BookRentalsDetail> bookRentalsDetails) throws SQLException, ClassNotFoundException {
        int i = 0;
        for (BookRentalsDetail b : bookRentalsDetails) {
            CrudUtil.setQuery("UPDATE Rentals set Avaliable_count=Avaliable_count - ? WHERE Rent_Id = ?", b.getQty(), b.getRentId());
            i++;
        }
        if (i == bookRentalsDetails.size()) {
            return true;
        }
        return false;
    }
}
