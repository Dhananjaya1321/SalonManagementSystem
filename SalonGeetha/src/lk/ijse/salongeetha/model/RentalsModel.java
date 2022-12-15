package lk.ijse.salongeetha.model;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.to.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RentalsModel {
    public static boolean addRentals(Rentals rentals) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection().
                prepareStatement("INSERT INTO Rentals VALUES (?,?,?,?,?,?)");
        preparedStatement.setObject(1, rentals.getRntId());
        preparedStatement.setObject(2, rentals.getName());
        preparedStatement.setObject(3, rentals.getPricePreDay());
        preparedStatement.setObject(4, rentals.getDescription());
        preparedStatement.setObject(5, rentals.getAvaliableCount());
        preparedStatement.setObject(6, rentals.getDiscount());
        int executeUpdate = preparedStatement.executeUpdate();
        if (executeUpdate > 0) {
            return true;
        }
        return false;
    }

    public static boolean deleteRentals(Rentals rentals) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("DELETE FROM Rentals WHERE Rent_Id=?");
        preparedStatement.setObject(1,rentals.getRntId());
        int executeUpdate = preparedStatement.executeUpdate();
        if (executeUpdate > 0) {
            return true;
        }
        return false;
    }
    public static boolean updateRentals(Rentals rentals) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("UPDATE Rentals SET Name=?,Price_pre_day=?,Description=?,Avaliable_count=?,Discount=? WHERE Rent_Id=?");
        preparedStatement.setObject(1,rentals.getName());
        preparedStatement.setObject(2,rentals.getPricePreDay());
        preparedStatement.setObject(3,rentals.getDescription());
        preparedStatement.setObject(4,rentals.getAvaliableCount());
        preparedStatement.setObject(5,rentals.getDiscount());
        preparedStatement.setObject(6,rentals.getRntId());
        int executeUpdate = preparedStatement.executeUpdate();
        if(executeUpdate > 0){
            return true;
        }
        return false;
    }

    public static String checkId() throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT Rent_Id FROM Rentals ORDER BY Rent_Id DESC LIMIT 1");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    public static ArrayList<Rentals> getAllRentals() throws SQLException, ClassNotFoundException {
            ArrayList<Rentals> rentals = new ArrayList<>();
            PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                    .prepareStatement("SELECT * FROM Rentals");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Rentals rental=new Rentals();
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
        ArrayList<Rentals> rentals=new ArrayList<>();
        String setColumn;
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(rental.getName());
        if (matcher.matches()) {
            setColumn="SELECT * FROM Rentals WHERE Name LIKE ?";
        }else {
            setColumn="SELECT * FROM Rentals WHERE Rent_Id LIKE ?";
        }
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection().prepareStatement(setColumn);
        prepareStatement.setObject(1,"%"+rental.getName()+"%");
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            Rentals searchRental=new Rentals();
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
        ArrayList<Rentals> rentals=new ArrayList<>();
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection().prepareStatement("SELECT * FROM Rentals WHERE Rent_Id=?");
        prepareStatement.setObject(1,rental.getRntId());
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            Rentals searchRental=new Rentals();
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
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("UPDATE Rentals set Avaliable_count=Avaliable_count - ? WHERE Rent_Id = ?");
        int i = 0;
        for(BookRentalsDetail b : bookRentalsDetails){
            prepareStatement.setObject(1,b.getQty());
            prepareStatement.setObject(1,b.getRentId());
            i++;
        }
        if (i==bookRentalsDetails.size()){
            return true;
        }


        return false;
    }
}
