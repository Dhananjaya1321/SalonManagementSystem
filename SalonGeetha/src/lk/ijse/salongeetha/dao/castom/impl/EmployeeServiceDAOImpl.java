package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.EmployeeServiceDAO;
import lk.ijse.salongeetha.entity.EmployeeServiceDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeServiceDAOImpl implements EmployeeServiceDAO {


    public boolean delete(EmployeeServiceDetail detail) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM employee_service_detail WHERE Emp_Id=? AND Sev_Id=?",
                detail.getEmpId(), detail.getSevId());
    }

    @Override
    public boolean checkAlreadyExists(EmployeeServiceDetail detail) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM employee_service_detail WHERE Emp_Id=? AND Sev_Id=?",
                detail.getEmpId(), detail.getSevId());
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    public boolean add(EmployeeServiceDetail detail) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO employee_service_detail VALUES (?,?)", detail.getEmpId(),
                detail.getSevId());
    }


    @Override
    public ArrayList<EmployeeServiceDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(EmployeeServiceDetail detail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<EmployeeServiceDetail> search(boolean value, EmployeeServiceDetail detail) throws SQLException, ClassNotFoundException {
        return null;
    }
}
