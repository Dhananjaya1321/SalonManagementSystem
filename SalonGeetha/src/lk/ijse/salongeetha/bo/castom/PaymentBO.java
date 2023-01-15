package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBOImpl {
    ResultSet getAllAppointmentPayments() throws SQLException, ClassNotFoundException;

    ResultSet getAllBookingPayments() throws SQLException, ClassNotFoundException;

    ArrayList<Appointment> getAppointmentIds() throws SQLException, ClassNotFoundException;

    ArrayList<Book> getBookingIds() throws SQLException, ClassNotFoundException;

    String checkAppointmentId() throws SQLException, ClassNotFoundException;

    String checkBookingId() throws SQLException, ClassNotFoundException;

    ResultSet getAmountDueBookRentalsDetail(BookRentalsDetail bookRentalsDetail) throws SQLException, ClassNotFoundException;

    ResultSet getAmountDueServiceAppointmentDetails(ServiceAppointmentDetail serviceAppointmentDetail) throws SQLException, ClassNotFoundException;

    boolean add(boolean value, Payment payment, Book book, Appointment appointment) throws SQLException, ClassNotFoundException;
}
