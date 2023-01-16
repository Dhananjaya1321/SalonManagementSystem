package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.BookRentalsDetailDTO;
import lk.ijse.salongeetha.to.RentalsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RentalsDAO extends SQLUtil<RentalsDTO> {

    ResultSet searchRentalsDetails(RentalsDTO rental) throws SQLException, ClassNotFoundException;

    boolean update(ArrayList<BookRentalsDetailDTO> bookRentalsDetailDTOS) throws SQLException, ClassNotFoundException;

}
