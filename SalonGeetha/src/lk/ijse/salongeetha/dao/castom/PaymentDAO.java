package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.to.PaymentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface PaymentDAO extends SQLUtil<PaymentDTO> {
    ResultSet getAllAPayments() throws SQLException, ClassNotFoundException;
    ResultSet getAllBPayments() throws SQLException, ClassNotFoundException;
    ResultSet searchPaymentDetails(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;

    boolean addBookingPayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;

    boolean addAppointmentPayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;

    String checkAppointmentId() throws SQLException, ClassNotFoundException;
    String checkBookId() throws SQLException, ClassNotFoundException;

}
