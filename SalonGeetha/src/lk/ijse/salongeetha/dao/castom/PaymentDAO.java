package lk.ijse.salongeetha.dao.castom;

import lk.ijse.salongeetha.dao.SQLUtil;
import lk.ijse.salongeetha.entity.AppointmentPayment;
import lk.ijse.salongeetha.entity.BookPayment;
import lk.ijse.salongeetha.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentDAO extends SQLUtil<AppointmentPayment> {
    ArrayList<AppointmentPayment> getAllAPayments() throws SQLException, ClassNotFoundException;
    ArrayList<BookPayment> getAllBPayments() throws SQLException, ClassNotFoundException;
    ArrayList<AppointmentPayment> searchPaymentDetails(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;

    boolean addBookingPayment(BookPayment paymentDTO) throws SQLException, ClassNotFoundException;

    boolean addAppointmentPayment(AppointmentPayment paymentDTO) throws SQLException, ClassNotFoundException;

    String checkAppointmentId() throws SQLException, ClassNotFoundException;
    String checkBookId() throws SQLException, ClassNotFoundException;

}
