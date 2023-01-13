package lk.ijse.salongeetha.model.castom;

import lk.ijse.salongeetha.model.SQLUtil;
import lk.ijse.salongeetha.to.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentDAO extends SQLUtil<Payment> {
    ResultSet getAllAPayments() throws SQLException, ClassNotFoundException;
    ResultSet getAllBPayments() throws SQLException, ClassNotFoundException;
    ResultSet searchPaymentDetails(Payment payment) throws SQLException, ClassNotFoundException;

    boolean addBookingPayment(Payment payment) throws SQLException, ClassNotFoundException;

    boolean addAppointmentPayment(Payment payment) throws SQLException, ClassNotFoundException;

    String checkAppointmentId() throws SQLException, ClassNotFoundException;
    String checkBookId() throws SQLException, ClassNotFoundException;

}
