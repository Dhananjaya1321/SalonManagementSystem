package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.BillDAO;
import lk.ijse.salongeetha.to.BillPaymentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDAOImpl implements BillDAO {
    public boolean add(BillPaymentDTO billPaymentDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Bill_payment VALUES (?,?,?,?,?,?)", billPaymentDTO.getBilId(), billPaymentDTO.getDate()
                , billPaymentDTO.getDescription(), billPaymentDTO.getTitle(), billPaymentDTO.getAmountPaid(), billPaymentDTO.getEmpId());
    }

    public String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Bil_Id FROM bill_payment ORDER BY Bil_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    @Override
    public ResultSet search(boolean value, BillPaymentDTO to) throws SQLException, ClassNotFoundException {
        String setColumn;
        if (value) {
            setColumn = "SELECT * FROM Bill_payment WHERE Title LIKE ?";
        } else {
            setColumn = "SELECT * FROM Bill_payment WHERE Bil_Id LIKE ?";
        }
        return CrudUtil.setQuery(setColumn, "%" + to.getTitle() + "%");
    }

    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM bill_payment");
    }

    public boolean delete(BillPaymentDTO billPaymentDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM bill_payment WHERE Bil_Id=?", billPaymentDTO.getBilId());
    }

    public boolean update(BillPaymentDTO billPaymentDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Bill_payment SET Date=?,Description=?,Title=?,Amount_paid=?,Emp_Id=? WHERE Bil_Id=?"
                , billPaymentDTO.getDate(), billPaymentDTO.getDescription(), billPaymentDTO.getTitle(), billPaymentDTO.getAmountPaid(), billPaymentDTO.getEmpId()
                , billPaymentDTO.getBilId());
    }


}
