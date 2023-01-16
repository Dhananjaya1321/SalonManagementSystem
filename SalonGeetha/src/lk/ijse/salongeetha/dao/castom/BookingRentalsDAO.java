package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.BookRentalsDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingRentalsDAO extends SQLUtil<BookRentalsDetailDTO> {
    boolean addDetails(ArrayList<BookRentalsDetailDTO> bookRentalsDetailDTOS) throws SQLException, ClassNotFoundException;
}
