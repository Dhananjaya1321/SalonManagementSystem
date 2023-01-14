package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.PaymentBO;
import lk.ijse.salongeetha.dao.castom.AppointmentDAO;
import lk.ijse.salongeetha.dao.castom.BookingDAO;
import lk.ijse.salongeetha.dao.castom.PaymentDAO;
import lk.ijse.salongeetha.dao.castom.QueryDAO;
import lk.ijse.salongeetha.dao.castom.impl.AppointmentDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.BookingDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.PaymentDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.QueryDAOImpl;
import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.to.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = new PaymentDAOImpl();
    BookingDAO booingDAO = new BookingDAOImpl();
    AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
    QueryDAO queryDAO = new QueryDAOImpl();
    BookingDAO bookingDAO = new BookingDAOImpl();

    @Override
    public ResultSet getAllAppointmentPayments() throws SQLException, ClassNotFoundException {
        return paymentDAO.getAllAPayments();
    }

    @Override
    public ResultSet getAllBookingPayments() throws SQLException, ClassNotFoundException {
        return paymentDAO.getAllAPayments();
    }

    @Override
    public ArrayList<Appointment> getAppointmentIds() throws SQLException, ClassNotFoundException {
        return appointmentDAO.getIds();
    }

    @Override
    public ArrayList<Book> getBookingIds() throws SQLException, ClassNotFoundException {
        return booingDAO.getIdS();
    }

    @Override
    public String checkAppointmentId() throws SQLException, ClassNotFoundException {
        return paymentDAO.checkAppointmentId();
    }

    @Override
    public String checkBookingId() throws SQLException, ClassNotFoundException {
        return paymentDAO.checkBookId();
    }

    @Override
    public ResultSet getAmountDueBookRentalsDetail(BookRentalsDetail bookRentalsDetail) throws SQLException, ClassNotFoundException {
        return queryDAO.getAmountDueBookRentalsDetail(bookRentalsDetail);
    }

    @Override
    public ResultSet getAmountDueServiceAppointmentDetails(ServiceAppointmentDetail serviceAppointmentDetail) throws SQLException, ClassNotFoundException {
        return queryDAO.getAmountDueServiceAppointmentDetails(serviceAppointmentDetail);
    }

    @Override
    public boolean add(boolean value, Payment payment, Book book, Appointment appointment) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        if (value) {
            bookingDAO.getId(payment);
            boolean isAdded = paymentDAO.addBookingPayment(payment);//set
            if (isAdded) {
                bookingDAO.update(book);
                DBConnection.getDBConnection().getConnection().commit();
                DBConnection.getDBConnection().getConnection().setAutoCommit(true);
                return true;
            }else{
                DBConnection.getDBConnection().getConnection().rollback();
                DBConnection.getDBConnection().getConnection().setAutoCommit(true);
                return false;
            }

        } else {
            appointmentDAO.getId(payment);
            boolean isAdded = paymentDAO.addAppointmentPayment(payment);//set
            if (isAdded) {
                appointmentDAO.update(appointment);
                DBConnection.getDBConnection().getConnection().commit();
                DBConnection.getDBConnection().getConnection().setAutoCommit(true);
                return true;
            }else {
                DBConnection.getDBConnection().getConnection().rollback();
                DBConnection.getDBConnection().getConnection().setAutoCommit(true);
                return false;
            }
        }
    }


}
