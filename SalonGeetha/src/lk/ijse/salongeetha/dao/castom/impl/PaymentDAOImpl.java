package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.PaymentDAO;
import lk.ijse.salongeetha.entity.AppointmentPayment;
import lk.ijse.salongeetha.entity.BookPayment;
import lk.ijse.salongeetha.dto.PaymentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {


    @Override
    public boolean addBookingPayment(BookPayment paymentDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO book_payment VALUES (?,?,?,?,?)",
                paymentDTO.getPay_Id(),
                paymentDTO.getPayment_method(),
                paymentDTO.getNIC(),
                paymentDTO.getAmount_due(),
                paymentDTO.getBok_Id()
        );
    }

    @Override
    public boolean addAppointmentPayment(AppointmentPayment paymentDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO appointment_payment VALUES (?,?,?,?,?)",
                paymentDTO.getPay_Id(),
                paymentDTO.getPayment_method(),
                paymentDTO.getNIC(),
                paymentDTO.getAmount_due(),
                paymentDTO.getApt_Id()
        );
    }

    @Override
    public boolean add(AppointmentPayment to) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean delete(AppointmentPayment paymentDTO) throws SQLException, ClassNotFoundException {
//        return CrudUtil.setQuery("DELETE FROM Payment WHERE Pay_Id=?", paymentDTO.getPayId());
        return false;
    }

    @Override
    public ArrayList<AppointmentPayment> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean update(AppointmentPayment paymentDTO) throws SQLException, ClassNotFoundException {
       return false;
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<AppointmentPayment> search(boolean value, AppointmentPayment to) throws SQLException, ClassNotFoundException {
        return null;
    }

    public String checkAppointmentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Pay_Id FROM appointment_payment ORDER BY Pay_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public String checkBookId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Pay_Id FROM book_payment ORDER BY Pay_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public ArrayList<AppointmentPayment> searchPaymentDetails(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return null;
    }

    public ArrayList<AppointmentPayment> getAllAPayments() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM appointment_payment");
        ArrayList<AppointmentPayment> arrayList = new ArrayList<>();
        while (resultSet.next()) {
            arrayList.add(
                    new AppointmentPayment(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4),
                            resultSet.getString(5)
                    )
            );
        }
        return arrayList;
    }

    public ArrayList<BookPayment> getAllBPayments() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM book_payment");
        ArrayList<BookPayment> arrayList = new ArrayList<>();
        while (resultSet.next()) {
            arrayList.add(
                    new BookPayment(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4),
                            resultSet.getString(5)
                    )
            );
        }
        return arrayList;
    }
}

