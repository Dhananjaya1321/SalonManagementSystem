package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.to.BillPaymentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BillBO extends SuperBOImpl {
    boolean deleteBillPayment(BillPaymentDTO billPaymentDTO) throws SQLException, ClassNotFoundException;

    boolean addBillPayment(BillPaymentDTO billPaymentDTO) throws SQLException, ClassNotFoundException;

    boolean updateBillPayment(BillPaymentDTO billPaymentDTO) throws SQLException, ClassNotFoundException;

    String checkIdBillPayment() throws SQLException, ClassNotFoundException;

    ResultSet searchBillPayment(boolean value, BillPaymentDTO billPaymentDTO) throws SQLException, ClassNotFoundException;

    ResultSet getAllBillPayment() throws SQLException, ClassNotFoundException;

    ResultSet getAllEmployees() throws SQLException, ClassNotFoundException;
}
