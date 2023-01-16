package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SuperDAOImpl;
import lk.ijse.salongeetha.to.BookRentalsDetailDTO;
import lk.ijse.salongeetha.to.ServiceAppointmentDetailDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface QueryDAO extends SuperDAOImpl {
    ResultSet getAmountDueServiceAppointmentDetails(ServiceAppointmentDetailDTO serviceAppointmentDetailDTO) throws SQLException, ClassNotFoundException;

    ResultSet getAllEmployeeServiceDetails() throws SQLException, ClassNotFoundException;

    ResultSet getAmountDueBookRentalsDetail(BookRentalsDetailDTO bookRentalsDetailDTO) throws SQLException, ClassNotFoundException;
}
