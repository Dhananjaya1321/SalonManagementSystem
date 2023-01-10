package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.model.CrudUtil;
import lk.ijse.salongeetha.to.Appointment;
import lk.ijse.salongeetha.to.Book;
import lk.ijse.salongeetha.to.Payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentModel {
    public static boolean addPayment(Payment payment) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement;
        Pattern namePattern = Pattern.compile("([BOK]{1,})([0-9]{1,})\\w+");
        Matcher matcher = namePattern.matcher(payment.getaOrBId());
        if (matcher.matches()) {
            BookingModel.getId(payment);
            boolean isAdded = CrudUtil.setQuery("INSERT INTO book_payment VALUES (?,?,?,?,?)"
                    , payment.getPayId(), payment.getPaymentMethod(), payment.getNic(), payment.getAmountDue()
                    , payment.getaOrBId());

            if (isAdded) {
                Book book = new Book();
                book.setBokId(payment.getaOrBId());
//                book.setDate(payment.getDate());
                book.setStatus("Paid");

                BookingModel.updateBooking(book);
                return true;
            }
            return false;
        } else {
            AppointmentModel.getId(payment);
            boolean isAdded = CrudUtil.setQuery("INSERT INTO appointment_payment VALUES (?,?,?,?,?)", payment.getPayId(), payment.getPaymentMethod()
                    , payment.getNic(), payment.getAmountDue(), payment.getaOrBId());
            if (isAdded) {
                Appointment appointment = new Appointment();
                appointment.setAptId(payment.getaOrBId());
                appointment.setStatus("Paid");
                AppointmentModel.updateAppointment(appointment);
                return true;
            }
            return false;
        }
    }

    public static boolean deletePayment(Payment payment) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Payment WHERE Pay_Id=?", payment.getPayId());
    }

    public static boolean updatePayment(Payment payment) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Payment SET Amount_paid=?,Balance=?,Payment_method=? WHERE Pay_Id=?",
                payment.getAmountPaid(), payment.getBalance(), payment.getPaymentMethod(), payment.getPayId());
    }

    public static String checkAppointmentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Pay_Id FROM appointment_payment ORDER BY Pay_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    public static String checkBookId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Pay_Id FROM book_payment ORDER BY Pay_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    public static void searchPaymentDetails(Payment payment) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT Pay_Id FROM payment ORDER BY Pay_Id DESC LIMIT 1");
        ResultSet resultSet = preparedStatement.executeQuery();
    }

    public static ArrayList<Payment> getAllAPayments() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> payments = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM appointment_payment");
        while (resultSet.next()) {
            Payment payment = new Payment();
            payment.setPayId(String.valueOf(resultSet.getObject(1)));
            payment.setPaymentMethod(String.valueOf(resultSet.getObject(2)));
            payment.setaOrBId(String.valueOf(resultSet.getObject(3)));
            payment.setAmountDue(Double.parseDouble(String.valueOf(resultSet.getObject(4))));
            payments.add(payment);
        }
        return payments;
    }

    public static ArrayList<Payment> getAllBPayments() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> payments = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM book_payment");
        while (resultSet.next()) {
            Payment payment = new Payment();
            payment.setPayId(String.valueOf(resultSet.getObject(1)));
            payment.setPaymentMethod(String.valueOf(resultSet.getObject(2)));
            payment.setaOrBId(String.valueOf(resultSet.getObject(3)));
            payment.setAmountDue(Double.parseDouble(String.valueOf(resultSet.getObject(4))));
            payments.add(payment);
        }
        return payments;
    }
}
