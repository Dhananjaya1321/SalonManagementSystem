package lk.ijse.salongeetha.model.castom;

import lk.ijse.salongeetha.model.SQLUtil;
import lk.ijse.salongeetha.to.BookRentalsDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingRentalsDAO extends SQLUtil<BookRentalsDetail> {
    boolean addDetails(ArrayList<BookRentalsDetail> bookRentalsDetails) throws SQLException, ClassNotFoundException;
}
