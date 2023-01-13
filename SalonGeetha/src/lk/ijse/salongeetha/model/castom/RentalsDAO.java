package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.BookRentalsDetail;
import lk.ijse.salongeetha.to.Rentals;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RentalsDAO extends SQLUtil<Rentals> {
    boolean update(ArrayList<BookRentalsDetail> bookRentalsDetails) throws SQLException, ClassNotFoundException;

}
