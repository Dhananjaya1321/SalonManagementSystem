package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.to.BookRentalsDetail;
import lk.ijse.salongeetha.to.Rentals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RentalsModel {
    public static boolean addRentals(Rentals rentals) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Rentals VALUES (?,?,?,?,?,?)", rentals.getRntId(), rentals.getName()
                , rentals.getPricePreDay(), rentals.getDescription(), rentals.getAvaliableCount(), rentals.getDiscount());
    }

    public static boolean deleteRentals(Rentals rentals) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Rentals WHERE Rent_Id=?", rentals.getRntId());
    }

    public static boolean updateRentals(Rentals rentals) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Rentals SET Name=?,Price_pre_day=?,Description=?,Avaliable_count=?,Discount=? " +
                "WHERE Rent_Id=?", rentals.getName(), rentals.getPricePreDay(), rentals.getDescription(), rentals.getAvaliableCount(), rentals.getDiscount(), rentals.getRntId());
    }

    public static String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Rent_Id FROM Rentals ORDER BY Rent_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    public static ArrayList<Rentals> getAllRentals() throws SQLException, ClassNotFoundException {
        ArrayList<Rentals> rentals = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM Rentals");
        while (resultSet.next()) {
            Rentals rental = new Rentals();
            rental.setRntId(String.valueOf(resultSet.getObject(1)));
            rental.setName(String.valueOf(resultSet.getObject(2)));
            rental.setPricePreDay((Double) resultSet.getObject(3));
            rental.setDescription(String.valueOf(resultSet.getObject(4)));
            rental.setAvaliableCount((Integer) resultSet.getObject(5));
            rental.setDiscount((Double) resultSet.getObject(6));
            rentals.add(rental);
        }
        return rentals;
    }

    public static ArrayList<Rentals> searchRentals(Rentals rental) throws SQLException, ClassNotFoundException {
        ArrayList<Rentals> rentals = new ArrayList<>();
        String setColumn;
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(rental.getName());
        if (matcher.matches()) {
            setColumn = "SELECT * FROM Rentals WHERE Name LIKE ?";
        } else {
            setColumn = "SELECT * FROM Rentals WHERE Rent_Id LIKE ?";
        }
        ResultSet resultSet = CrudUtil.setQuery(setColumn, "%" + rental.getName() + "%");
        while (resultSet.next()) {
            Rentals searchRental = new Rentals();
            searchRental.setRntId(String.valueOf(resultSet.getObject(1)));
            searchRental.setName(String.valueOf(resultSet.getObject(2)));
            searchRental.setPricePreDay((Double) resultSet.getObject(3));
            searchRental.setDescription(String.valueOf(resultSet.getObject(4)));
            searchRental.setAvaliableCount((Integer) resultSet.getObject(5));
            searchRental.setDiscount((Double) resultSet.getObject(6));
            rentals.add(searchRental);
        }
        return rentals;
    }

    public static ArrayList<Rentals> searchRentalsDetails(Rentals rental) throws SQLException, ClassNotFoundException {
        ArrayList<Rentals> rentals = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM Rentals WHERE Rent_Id=?", rental.getRntId());
        while (resultSet.next()) {
            Rentals searchRental = new Rentals();
            searchRental.setRntId(String.valueOf(resultSet.getObject(1)));
            searchRental.setName(String.valueOf(resultSet.getObject(2)));
            searchRental.setPricePreDay((Double) resultSet.getObject(3));
            searchRental.setDescription(String.valueOf(resultSet.getObject(4)));
            searchRental.setAvaliableCount((Integer) resultSet.getObject(5));
            searchRental.setDiscount((Double) resultSet.getObject(6));
            rentals.add(searchRental);
        }
        return rentals;
    }

    public static boolean updateRentalQty(ArrayList<BookRentalsDetail> bookRentalsDetails) throws SQLException, ClassNotFoundException {
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
