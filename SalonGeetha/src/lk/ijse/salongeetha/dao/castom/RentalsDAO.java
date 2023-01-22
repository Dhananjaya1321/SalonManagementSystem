package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.entity.BookRentalsDetail;
import lk.ijse.salongeetha.entity.Rentals;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RentalsDAO extends SQLUtil<Rentals> {

    ArrayList<Rentals> searchRentalsDetails(Rentals rental) throws SQLException, ClassNotFoundException;

    boolean update(ArrayList<BookRentalsDetail> bookRentalsDetailDTOS) throws SQLException, ClassNotFoundException;

}
