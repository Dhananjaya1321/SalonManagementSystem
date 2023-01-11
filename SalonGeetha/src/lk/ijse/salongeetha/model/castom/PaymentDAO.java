package lk.ijse.salongeetha.model.castom;

import lk.ijse.salongeetha.model.SQLUtil;
import lk.ijse.salongeetha.to.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentDAO extends SQLUtil<Payment> {
    ArrayList<Payment> getAllAPayments() throws SQLException, ClassNotFoundException;
    ArrayList<Payment> getAllBPayments() throws SQLException, ClassNotFoundException;
    void searchPaymentDetails(Payment payment) throws SQLException, ClassNotFoundException;
    String checkAppointmentId() throws SQLException, ClassNotFoundException;
    String checkBookId() throws SQLException, ClassNotFoundException;

}
