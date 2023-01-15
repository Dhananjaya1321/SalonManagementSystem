package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.BillBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.BillDAO;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.dao.castom.impl.BillDAOImpl;
import lk.ijse.salongeetha.dao.castom.impl.EmployeeDAOImpl;
import lk.ijse.salongeetha.to.BillPayment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillBOImpl implements BillBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.EMPLOYEE);
    BillDAO billPaymentDAO = (BillDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.BILL);

    @Override
    public boolean deleteBillPayment(BillPayment billPayment) throws SQLException, ClassNotFoundException {
        return billPaymentDAO.delete(billPayment);
    }

    @Override
    public boolean addBillPayment(BillPayment billPayment) throws SQLException, ClassNotFoundException {
        return billPaymentDAO.add(billPayment);
    }

    @Override
    public boolean updateBillPayment(BillPayment billPayment) throws SQLException, ClassNotFoundException {
        return billPaymentDAO.update(billPayment);
    }

    @Override
    public String checkIdBillPayment() throws SQLException, ClassNotFoundException {
        return billPaymentDAO.checkId();
    }

    @Override
    public ResultSet searchBillPayment(boolean value, BillPayment billPayment) throws SQLException, ClassNotFoundException {
        return billPaymentDAO.search(value, billPayment);
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
