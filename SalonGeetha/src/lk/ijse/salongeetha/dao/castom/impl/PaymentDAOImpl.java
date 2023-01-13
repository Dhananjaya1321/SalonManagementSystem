package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.PaymentDAO;
import lk.ijse.salongeetha.to.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAOImpl implements PaymentDAO {


    @Override
    public boolean addBookingPayment(Payment payment) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO book_payment VALUES (?,?,?,?,?)", payment.getPayId(),
                payment.getPaymentMethod(), payment.getNic(), payment.getAmountDue(), payment.getaOrBId());
    }

    @Override
    public boolean addAppointmentPayment(Payment payment) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO appointment_payment VALUES (?,?,?,?,?)", payment.getPayId(), payment.getPaymentMethod()
                , payment.getNic(), payment.getAmountDue(), payment.getaOrBId());
    }
//    public boolean add(Payment payment) throws SQLException, ClassNotFoundException {
//        if (matcher.matches()) {
//            bookingDAO.getId(payment);
//            boolean isAdded =
//
//            if (isAdded) {
//                Book book = new Book();
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
//                Appointment appointment = new Appointment();
//                appointment.setAptId(payment.getaOrBId());
//                appointment.setStatus("Paid");
//                appointmentDAO.update(appointment);
//                return true;
//            }
//            return false;
//        }
//    }

    @Override
    public boolean add(Payment to) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean delete(Payment payment) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Payment WHERE Pay_Id=?", payment.getPayId());
    }

    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean update(Payment payment) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Payment SET Amount_paid=?,Balance=?,Payment_method=? WHERE Pay_Id=?",
                payment.getAmountPaid(), payment.getBalance(), payment.getPaymentMethod(), payment.getPayId());
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet search(boolean value, Payment to) throws SQLException, ClassNotFoundException {
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

    public ResultSet searchPaymentDetails(Payment payment) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT Pay_Id FROM payment ORDER BY Pay_Id DESC LIMIT 1");
    }

    public ResultSet getAllAPayments() throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM appointment_payment");
    }

    public ResultSet getAllBPayments() throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM book_payment");
    }
}

