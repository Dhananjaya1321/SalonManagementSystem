package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.to.BillPayment;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BillBO {
    boolean deleteBillPayment(BillPayment billPayment) throws SQLException, ClassNotFoundException;

    boolean addBillPayment(BillPayment billPayment) throws SQLException, ClassNotFoundException;

    boolean updateBillPayment(BillPayment billPayment) throws SQLException, ClassNotFoundException;

    String checkIdBillPayment() throws SQLException, ClassNotFoundException;

    ResultSet searchBillPayment(boolean value, BillPayment billPayment) throws SQLException, ClassNotFoundException;

    ResultSet getAllBillPayment() throws SQLException, ClassNotFoundException;

    ResultSet getAllEmployees() throws SQLException, ClassNotFoundException;
}
