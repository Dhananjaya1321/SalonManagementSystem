package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.PaymentDAO;
import lk.ijse.salongeetha.to.PaymentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAOImpl implements PaymentDAO {


    @Override
    public boolean addBookingPayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO book_payment VALUES (?,?,?,?,?)", paymentDTO.getPayId(),
                paymentDTO.getPaymentMethod(), paymentDTO.getNic(), paymentDTO.getAmountDue(), paymentDTO.getaOrBId());
    }

    @Override
    public boolean addAppointmentPayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO appointment_payment VALUES (?,?,?,?,?)", paymentDTO.getPayId(), paymentDTO.getPaymentMethod()
                , paymentDTO.getNic(), paymentDTO.getAmountDue(), paymentDTO.getaOrBId());
    }
//    public boolean add(PaymentDTO payment) throws SQLException, ClassNotFoundException {
//        if (matcher.matches()) {
//            bookingDAO.getId(payment);
//            boolean isAdded =
//
//            if (isAdded) {
//                BookDTO book = new BookDTO();
//                book.setBokId(payment.getaOrBId());
////                book.setDate(payment.getDate());
//                book.setStatus("Paid");
//
//                bookingDAO.update(book);
//                return true;
//            }
//            return false;
//        } else {
//            appointmentDAO.getId(payment);
//            boolean isAdded =
//            if (isAdded) {
//                AppointmentDTO Appointment = new AppointmentDTO();
//                Appointment.setAptId(payment.getaOrBId());
//                Appointment.setStatus("Paid");
//                appointmentDAO.update(Appointment);
//                return true;
//            }
//            return false;
//        }
//    }

    @Override
    public boolean add(PaymentDTO to) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean delete(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Payment WHERE Pay_Id=?", paymentDTO.getPayId());
    }

    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean update(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Payment SET Amount_paid=?,Balance=?,Payment_method=? WHERE Pay_Id=?",
                paymentDTO.getAmountPaid(), paymentDTO.getBalance(), paymentDTO.getPaymentMethod(), paymentDTO.getPayId());
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet search(boolean value, PaymentDTO to) throws SQLException, ClassNotFoundException {
        return null;
    }


    public String checkAppointmentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Pay_Id FROM appointment_payment ORDER BY Pay_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    public String checkBookId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Pay_Id FROM book_payment ORDER BY Pay_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    public ResultSet searchPaymentDetails(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT Pay_Id FROM payment ORDER BY Pay_Id DESC LIMIT 1");
    }

    public ResultSet getAllAPayments() throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM appointment_payment");
    }

    public ResultSet getAllBPayments() throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM book_payment");
    }
}

