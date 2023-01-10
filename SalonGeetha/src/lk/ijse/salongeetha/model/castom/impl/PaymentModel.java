package lk.ijse.salongeetha.model.castom.impl;

import lk.ijse.salongeetha.db.DBConnection;
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
             preparedStatement = DBConnection.getDBConnection().getConnection().
                    prepareStatement("INSERT INTO book_payment VALUES (?,?,?,?,?)");
            preparedStatement.setObject(1, payment.getPayId());
            preparedStatement.setObject(2, payment.getPaymentMethod());
            preparedStatement.setObject(3, payment.getNic());
            preparedStatement.setObject(4, payment.getAmountDue());
            preparedStatement.setObject(5, payment.getaOrBId());
            int executeUpdate = preparedStatement.executeUpdate();
            if (executeUpdate > 0) {
                Book book=new Book();
                book.setBokId(payment.getaOrBId());
//                book.setDate(payment.getDate());
                book.setStatus("Paid");

                BookingModel.updateBooking(book);
                return true;
            }
            return false;
        }else {
            AppointmentModel.getId(payment);
             preparedStatement = DBConnection.getDBConnection().getConnection().
                    prepareStatement("INSERT INTO appointment_payment VALUES (?,?,?,?,?)");
            preparedStatement.setObject(1, payment.getPayId());
            preparedStatement.setObject(2, payment.getPaymentMethod());
            preparedStatement.setObject(3, payment.getNic());
            preparedStatement.setObject(4, payment.getAmountDue());
            preparedStatement.setObject(5, payment.getaOrBId());
            int executeUpdate = preparedStatement.executeUpdate();
            if (executeUpdate > 0) {
//                System.out.println(payment.getaOrBId());
                Appointment appointment=new Appointment();

                appointment.setAptId(payment.getaOrBId());
//                appointment.setTime(payment.getTime());
//                appointment.setDate(payment.getDate());
                appointment.setStatus("Paid");
//                System.out.println(payment.getaOrBId());
                AppointmentModel.updateAppointment(appointment);
                return true;
            }
            return false;
        }
    }

    public static boolean deletePayment(Payment payment) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("DELETE FROM Payment WHERE Pay_Id=?");
        preparedStatement.setObject(1,payment.getPayId());
        int executeUpdate = preparedStatement.executeUpdate();
        if (executeUpdate > 0) {
            return true;
        }
        return false;
    }
    public static boolean updatePayment(Payment payment) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("UPDATE Payment SET Amount_paid=?,Balance=?,Payment_method=? WHERE Pay_Id=?");
        preparedStatement.setObject(1,payment.getAmountPaid());
        preparedStatement.setObject(2,payment.getBalance());
        preparedStatement.setObject(3,payment.getPaymentMethod());
        preparedStatement.setObject(4,payment.getPayId());
        int executeUpdate = preparedStatement.executeUpdate();
        if(executeUpdate > 0){
            return true;
        }
        return false;
    }

    public static String checkAppointmentId() throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT Pay_Id FROM appointment_payment ORDER BY Pay_Id DESC LIMIT 1");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));

        }
        return null;
    }
    public static String checkBookId() throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT Pay_Id FROM book_payment ORDER BY Pay_Id DESC LIMIT 1");
        ResultSet resultSet = preparedStatement.executeQuery();
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
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT * FROM appointment_payment");
        ResultSet resultSet = preparedStatement.executeQuery();
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
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT * FROM book_payment");
        ResultSet resultSet = preparedStatement.executeQuery();
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

    /*public static ArrayList<Payment> searchService(Payment payment) {
        ArrayList<Payment> payments = new ArrayList<>();
        String setColumn;
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(payment.getPaymentMethod());
        if (matcher.matches()) {
            setColumn = "SELECT * FROM Service WHERE Name LIKE ?";
        } else {
            setColumn = "SELECT * FROM Service WHERE Sev_Id LIKE ?";
        }

        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection().prepareStatement(setColumn);
        prepareStatement.setObject(1, "%" + service.getName() + "%");
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            Service searchService = new Service();
            searchService.setSevId(String.valueOf(resultSet.getObject(1)));
            searchService.setDescription(String.valueOf(resultSet.getObject(2)));
            searchService.setName(String.valueOf(resultSet.getObject(3)));
            searchService.setPrice(Double.parseDouble(String.valueOf(resultSet.getObject(4))));
            searchService.setDiscount(Double.parseDouble(String.valueOf(resultSet.getObject(5))));
            payments.add(searchService);
        }
        return payments;
    }*/
}

