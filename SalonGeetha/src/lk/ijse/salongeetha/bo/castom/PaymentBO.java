package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBOImpl {
    ResultSet getAllAppointmentPayments() throws SQLException, ClassNotFoundException;

    ResultSet getAllBookingPayments() throws SQLException, ClassNotFoundException;

    ArrayList<AppointmentDTO> getAppointmentIds() throws SQLException, ClassNotFoundException;

    ArrayList<BookDTO> getBookingIds() throws SQLException, ClassNotFoundException;

    String checkAppointmentId() throws SQLException, ClassNotFoundException;

    String checkBookingId() throws SQLException, ClassNotFoundException;

    ResultSet getAmountDueBookRentalsDetail(BookRentalsDetailDTO bookRentalsDetailDTO) throws SQLException, ClassNotFoundException;

    ResultSet getAmountDueServiceAppointmentDetails(ServiceAppointmentDetailDTO serviceAppointmentDetailDTO) throws SQLException, ClassNotFoundException;

    boolean add(boolean value, PaymentDTO paymentDTO, BookDTO bookDTO, AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException;
}
