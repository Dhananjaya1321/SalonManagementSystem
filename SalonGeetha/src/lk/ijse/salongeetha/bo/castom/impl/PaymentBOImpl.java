package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.PaymentBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.AppointmentDAO;
import lk.ijse.salongeetha.dao.castom.BookingDAO;
import lk.ijse.salongeetha.dao.castom.PaymentDAO;
import lk.ijse.salongeetha.dao.castom.QueryDAO;
import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.to.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.PAYMENT);
    BookingDAO booingDAO = (BookingDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.BOOKING);
    AppointmentDAO appointmentDAO = (AppointmentDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.APPOINTMENT);
    QueryDAO queryDAO = (QueryDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.QUERY);
    BookingDAO bookingDAO = (BookingDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.BOOKING);

    @Override
    public ResultSet getAllAppointmentPayments() throws SQLException, ClassNotFoundException {
        return paymentDAO.getAllAPayments();
    }

    @Override
    public ResultSet getAllBookingPayments() throws SQLException, ClassNotFoundException {
        return paymentDAO.getAllAPayments();
    }

    @Override
    public ArrayList<AppointmentDTO> getAppointmentIds() throws SQLException, ClassNotFoundException {
        return appointmentDAO.getIds();
    }

    @Override
    public ArrayList<BookDTO> getBookingIds() throws SQLException, ClassNotFoundException {
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
    public ResultSet getAmountDueBookRentalsDetail(BookRentalsDetailDTO bookRentalsDetailDTO) throws SQLException, ClassNotFoundException {
        return queryDAO.getAmountDueBookRentalsDetail(bookRentalsDetailDTO);
    }

    @Override
    public ResultSet getAmountDueServiceAppointmentDetails(ServiceAppointmentDetailDTO serviceAppointmentDetailDTO) throws SQLException, ClassNotFoundException {
        return queryDAO.getAmountDueServiceAppointmentDetails(serviceAppointmentDetailDTO);
    }

    @Override
    public boolean add(boolean value, PaymentDTO paymentDTO, BookDTO bookDTO, AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        if (value) {
            bookingDAO.getId(paymentDTO);
            boolean isAdded = paymentDAO.addBookingPayment(paymentDTO);//set
            if (isAdded) {
                bookingDAO.update(bookDTO);
                DBConnection.getDBConnection().getConnection().commit();
                DBConnection.getDBConnection().getConnection().setAutoCommit(true);
                return true;
            }else{
                DBConnection.getDBConnection().getConnection().rollback();
                DBConnection.getDBConnection().getConnection().setAutoCommit(true);
                return false;
            }

        } else {
            appointmentDAO.getId(paymentDTO);
            boolean isAdded = paymentDAO.addAppointmentPayment(paymentDTO);//set
            if (isAdded) {
                appointmentDAO.update(appointmentDTO);
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
