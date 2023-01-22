package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.RentalsDAO;
import lk.ijse.salongeetha.entity.BookRentalsDetail;
import lk.ijse.salongeetha.entity.Rentals;

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
            return resultSet.getString(1);
        }
        return null;
    }


    public ArrayList<Rentals> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Rentals> arrayList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM Rentals");
        while (resultSet.next()) {
            arrayList.add(
                    new Rentals(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(4),
                            resultSet.getInt(3),
                            resultSet.getDouble(5),
                            resultSet.getDouble(6)
                    )
            );
        }
        return arrayList;
    }

    public ArrayList<Rentals> search(boolean value, Rentals rental) throws SQLException, ClassNotFoundException {
        String setColumn;
        if (value) {
            setColumn = "SELECT * FROM Rentals WHERE Name LIKE ?";
        } else {
            setColumn = "SELECT * FROM Rentals WHERE Rent_Id LIKE ?";
        }
        ArrayList<Rentals> arrayList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery(setColumn, "%" + rental.getName() + "%");
        while (resultSet.next()) {
            arrayList.add(new Rentals(resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(4),
                            resultSet.getInt(3),
                            resultSet.getDouble(5),
                            resultSet.getDouble(6)
                    )
            );
        }
        return arrayList;
    }

    @Override
    public ArrayList<Rentals> searchRentalsDetails(Rentals rental) throws SQLException, ClassNotFoundException {
        ArrayList<Rentals> arrayList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM Rentals WHERE Rent_Id=?", rental.getRntId());
        while (resultSet.next()) {
            arrayList.add(
                    new Rentals(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(4),
                            resultSet.getInt(3),
                            resultSet.getDouble(5),
                            resultSet.getDouble(6)
                    )
            );
        }
        return arrayList;
    }

    public boolean update(ArrayList<BookRentalsDetail> details) throws SQLException, ClassNotFoundException {
        int i = 0;
        for (BookRentalsDetail b : details) {
            CrudUtil.setQuery("UPDATE Rentals set Avaliable_count=Avaliable_count - ? WHERE Rent_Id = ?", b.getQty(), b.getRentId());
            i++;
        }
        if (i == details.size()) {
            return true;
        }
        return false;
    }
}
