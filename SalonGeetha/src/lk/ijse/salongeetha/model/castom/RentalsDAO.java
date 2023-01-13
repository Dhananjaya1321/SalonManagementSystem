package lk.ijse.salongeetha.model.castom;

import lk.ijse.salongeetha.model.SQLUtil;
import lk.ijse.salongeetha.to.BookRentalsDetail;
import lk.ijse.salongeetha.to.Rentals;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RentalsDAO extends SQLUtil<Rentals> {
    boolean update(ArrayList<BookRentalsDetail> bookRentalsDetails) throws SQLException, ClassNotFoundException;

}
