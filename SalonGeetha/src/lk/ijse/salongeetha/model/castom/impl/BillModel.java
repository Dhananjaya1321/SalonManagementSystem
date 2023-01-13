package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.BillDAO;
import lk.ijse.salongeetha.to.BillPayment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BillModel implements BillDAO {
    public boolean add(BillPayment billPayment) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Bill_payment VALUES (?,?,?,?,?,?)", billPayment.getBilId(), billPayment.getDate()
                , billPayment.getDescription(), billPayment.getTitle(), billPayment.getAmountPaid(), billPayment.getEmpId());
    }

    public String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Bil_Id FROM bill_payment ORDER BY Bil_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    public ArrayList<BillPayment> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<BillPayment> billPayments = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM bill_payment");
        while (resultSet.next()) {
            BillPayment billPayment = new BillPayment();
            billPayment.setBilId(String.valueOf(resultSet.getObject(1)));
            billPayment.setDate(String.valueOf(resultSet.getObject(2)));
            billPayment.setDescription(String.valueOf(resultSet.getObject(3)));
            billPayment.setTitle(String.valueOf(resultSet.getObject(4)));
            billPayment.setAmountPaid((Double) resultSet.getObject(5));
            billPayment.setEmpId(String.valueOf(resultSet.getObject(6)));
            billPayments.add(billPayment);
        }
        return billPayments;
    }

    public boolean delete(BillPayment billPayment) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM bill_payment WHERE Bil_Id=?", billPayment.getBilId());
    }

    public boolean update(BillPayment billPayment) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Bill_payment SET Date=?,Description=?,Title=?,Amount_paid=?,Emp_Id=? WHERE Bil_Id=?"
                , billPayment.getDate(), billPayment.getDescription(), billPayment.getTitle(), billPayment.getAmountPaid(), billPayment.getEmpId()
                , billPayment.getBilId());
    }

    public ArrayList<BillPayment> search(BillPayment billPayment) throws SQLException, ClassNotFoundException {
        ArrayList<BillPayment> billPayments = new ArrayList<>();
        String setColumn;
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(billPayment.getTitle());
        if (matcher.matches()) {
            setColumn = "SELECT * FROM Bill_payment WHERE Title LIKE ?";
        } else {
            setColumn = "SELECT * FROM Bill_payment WHERE Bil_Id LIKE ?";
        }
        ResultSet resultSet = CrudUtil.setQuery(setColumn, "%" + billPayment.getTitle() + "%");
        while (resultSet.next()) {
            BillPayment searchBillPayment = new BillPayment();
            searchBillPayment.setBilId(String.valueOf(resultSet.getObject(1)));
            searchBillPayment.setDate(String.valueOf(resultSet.getObject(2)));
            searchBillPayment.setDescription(String.valueOf(resultSet.getObject(3)));
            searchBillPayment.setTitle(String.valueOf(resultSet.getObject(4)));
            searchBillPayment.setAmountPaid((Double) resultSet.getObject(5));
            searchBillPayment.setEmpId(String.valueOf(resultSet.getObject(6)));
            billPayments.add(searchBillPayment);
        }
        return billPayments;
    }
}
