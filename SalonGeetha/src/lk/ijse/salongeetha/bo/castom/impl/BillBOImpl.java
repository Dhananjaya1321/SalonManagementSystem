package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.BillBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.BillDAO;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.to.BillPaymentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillBOImpl implements BillBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.EMPLOYEE);
    BillDAO billPaymentDAO = (BillDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.BILL);

    @Override
    public boolean deleteBillPayment(BillPaymentDTO billPaymentDTO) throws SQLException, ClassNotFoundException {
        return billPaymentDAO.delete(billPaymentDTO);
    }

    @Override
    public boolean addBillPayment(BillPaymentDTO billPaymentDTO) throws SQLException, ClassNotFoundException {
        return billPaymentDAO.add(billPaymentDTO);
    }

    @Override
    public boolean updateBillPayment(BillPaymentDTO billPaymentDTO) throws SQLException, ClassNotFoundException {
        return billPaymentDAO.update(billPaymentDTO);
    }

    @Override
    public String checkIdBillPayment() throws SQLException, ClassNotFoundException {
        return billPaymentDAO.checkId();
    }

    @Override
    public ResultSet searchBillPayment(boolean value, BillPaymentDTO billPaymentDTO) throws SQLException, ClassNotFoundException {
        return billPaymentDAO.search(value, billPaymentDTO);
    }

    @Override
    public ResultSet getAllBillPayment() throws SQLException, ClassNotFoundException {
        return billPaymentDAO.getAll();
    }

    @Override
    public ResultSet getAllEmployees() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAll();
    }
}
