package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.PaymentBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.AppointmentDAO;
import lk.ijse.salongeetha.dao.castom.BookingDAO;
import lk.ijse.salongeetha.dao.castom.PaymentDAO;
import lk.ijse.salongeetha.dao.castom.QueryDAO;
import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.entity.*;
import lk.ijse.salongeetha.dto.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.PAYMENT);
    BookingDAO booingDAO = (BookingDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.BOOKING);
    AppointmentDAO appointmentDAO = (AppointmentDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.APPOINTMENT);
    QueryDAO queryDAO = (QueryDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.QUERY);
    BookingDAO bookingDAO = (BookingDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.BOOKING);
    private ArrayList<PaymentDTO> paymentDTOS;

    @Override
    public ArrayList<PaymentDTO> getAllAppointmentPayments() throws SQLException, ClassNotFoundException {
        /*ArrayList<AppointmentPayment>*/
        ArrayList<AppointmentPayment> allAPayments = paymentDAO.getAllAPayments();
        /*ArrayList<PaymentDTO>*/
        paymentDTOS = new ArrayList<>();
        for (AppointmentPayment a : allAPayments) {
            paymentDTOS.add(new PaymentDTO(
                    a.getPay_Id(),
                    a.getPayment_method(),
                    a.getNIC(),
                    a.getAmount_due(),
                    a.getApt_Id()));
        }
        return paymentDTOS;
    }

    @Override
    public ArrayList<PaymentDTO> getAllBookingPayments() throws SQLException, ClassNotFoundException {
        /*ArrayList<BookPayment>*/
        ArrayList<BookPayment> allBPayments = paymentDAO.getAllBPayments();
        /*ArrayList<PaymentDTO>*/
        paymentDTOS = new ArrayList<>();
        for (BookPayment a : allBPayments) {
            paymentDTOS.add(new PaymentDTO(
                    a.getPay_Id(),
                    a.getPayment_method(),
                    a.getNIC(),
                    a.getAmount_due(),
                    a.getBok_Id()));
        }
        return paymentDTOS;
    }

    @Override
    public ArrayList<AppointmentDTO> getAppointmentIds() throws SQLException, ClassNotFoundException {
        ArrayList<Appointment> arrayList = appointmentDAO.getIds();
        ArrayList<AppointmentDTO> dtos = new ArrayList<>();
        for (Appointment a : arrayList) {
            dtos.add(new AppointmentDTO(a.getAptId(), a.getDate(), a.getTime(), a.getNic()));
        }
        return dtos;
    }

    @Override
    public ArrayList<BookDTO> getBookingIds() throws SQLException, ClassNotFoundException {
        ArrayList<Book> arrayList = booingDAO.getIdS();
        ArrayList<BookDTO> dtos = new ArrayList<>();
        for (Book b : arrayList) {
            dtos.add(new BookDTO(b.getBokId(), b.getDate(), b.getNic()));
        }
        return dtos;
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
    public ArrayList<CustomDTO> getAmountDueBookRentalsDetail(BookRentalsDetailDTO dto) throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> arrayList = queryDAO.getAmountDueBookRentalsDetail(new BookRentalsDetail(dto.getRentId(),
                dto.getBokId(), dto.getForHowManyDays(), dto.getQty()));
        ArrayList<CustomDTO> dtos = new ArrayList<>();
        for (CustomEntity c : arrayList) {
            dtos.add(new CustomDTO(c.getQty(), c.getForHowManyDays(), c.getPrice(), c.getDiscount()));
        }
        return dtos;
    }

    @Override
    public ArrayList<CustomDTO> getAmountDueServiceAppointmentDetails(ServiceAppointmentDetailDTO dto) throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> arrayList = queryDAO.getAmountDueServiceAppointmentDetails(new ServiceAppointmentDetail(dto.getAptId(), dto.getSevId()));
        ArrayList<CustomDTO> dtos = new ArrayList<>();
        for (CustomEntity c : arrayList) {
            dtos.add(new CustomDTO(c.getPrice(), c.getDiscount()));
        }
        return dtos;
    }

    @Override
    public boolean add(boolean value, PaymentDTO paymentDTO, BookDTO bookDTO, AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {
            if (value) {
                String nic = bookingDAO.getId(new BookPayment(paymentDTO.getPayId(), paymentDTO.getPaymentMethod(), paymentDTO.getNic(),
                        paymentDTO.getAmountDue(), paymentDTO.getaOrBId()));
                boolean isAdded = paymentDAO.addBookingPayment(
                        new BookPayment(
                                paymentDTO.getPayId(),
                                paymentDTO.getPaymentMethod(),
                                nic,
                                paymentDTO.getAmountDue(),
                                paymentDTO.getaOrBId()));//set

                if (isAdded) {
                    bookingDAO.update(new Book(bookDTO.getBokId(), bookDTO.getDate(), bookDTO.getNic(), bookDTO.getStatus()));
                    DBConnection.getDBConnection().getConnection().commit();
//                    DBConnection.getDBConnection().getConnection().setAutoCommit(true);
                    return true;
                } else {
                    DBConnection.getDBConnection().getConnection().rollback();
//                    DBConnection.getDBConnection().getConnection().setAutoCommit(true);
                    return false;
                }

            } else {
                String nic = appointmentDAO.getId(new AppointmentPayment(paymentDTO.getPayId(), paymentDTO.getPaymentMethod(),
                        paymentDTO.getNic(), paymentDTO.getAmountDue(), paymentDTO.getaOrBId()));
                boolean isAdded = paymentDAO.addAppointmentPayment(new AppointmentPayment(
                        paymentDTO.getPayId(),
                        paymentDTO.getPaymentMethod(),
                        nic,
                        paymentDTO.getAmountDue(),
                        paymentDTO.getaOrBId()));//set
//            System.out.println(paymentDTO.getNic());
                if (isAdded) {
                    appointmentDAO.update(new Appointment(appointmentDTO.getAptId(), appointmentDTO.getDate(), appointmentDTO.getTime()
                            , appointmentDTO.getNic(), appointmentDTO.getStatus()));
                    DBConnection.getDBConnection().getConnection().commit();
//                    DBConnection.getDBConnection().getConnection().setAutoCommit(true);
                    return true;
                } else {
                    DBConnection.getDBConnection().getConnection().rollback();
//                    DBConnection.getDBConnection().getConnection().setAutoCommit(true);
                    return false;
                }
            }
        } finally {
            DBConnection.getDBConnection().getConnection().setAutoCommit(true);
        }
    }
}
