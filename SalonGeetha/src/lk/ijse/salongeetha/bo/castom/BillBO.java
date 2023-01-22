package lk.ijse.salongeetha.bo.castom;

import lk.ijse.salongeetha.bo.SuperBOImpl;
import lk.ijse.salongeetha.dto.BillPaymentDTO;
import lk.ijse.salongeetha.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BillBO extends SuperBOImpl {
    boolean deleteBillPayment(BillPaymentDTO billPaymentDTO) throws SQLException, ClassNotFoundException;

    boolean addBillPayment(BillPaymentDTO billPaymentDTO) throws SQLException, ClassNotFoundException;

    boolean updateBillPayment(BillPaymentDTO billPaymentDTO) throws SQLException, ClassNotFoundException;

    String checkIdBillPayment() throws SQLException, ClassNotFoundException;

    ArrayList<BillPaymentDTO> searchBillPayment(boolean value, BillPaymentDTO billPaymentDTO) throws SQLException, ClassNotFoundException;

    ArrayList<BillPaymentDTO> getAllBillPayment() throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException;
}
