package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.entity.BookRentalsDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingRentalsDAO extends SQLUtil<BookRentalsDetail> {
    boolean addDetails(ArrayList<BookRentalsDetail> bookRentalsDetailD) throws SQLException, ClassNotFoundException;
}
