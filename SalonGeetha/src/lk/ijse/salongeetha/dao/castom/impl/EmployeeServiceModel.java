package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.EmployeeServiceDAO;
import lk.ijse.salongeetha.to.EmployeeServiceDetail;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeServiceModel implements EmployeeServiceDAO {




    public boolean delete(EmployeeServiceDetail employeeServiceDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM employee_service_detail WHERE Emp_Id=? AND Sev_Id=?",
                employeeServiceDetail.getEmpId(), employeeServiceDetail.getSevId());
    }
@Override
    public boolean checkAlreadyExists(EmployeeServiceDetail employeeServiceDetail) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM employee_service_detail WHERE Emp_Id=? AND Sev_Id=?",
                employeeServiceDetail.getEmpId(), employeeServiceDetail.getSevId());
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    public boolean add(EmployeeServiceDetail employeeServiceDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO employee_service_detail VALUES (?,?)", employeeServiceDetail.getEmpId(),
                employeeServiceDetail.getSevId());
    }





    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(EmployeeServiceDetail to) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet search(boolean value, EmployeeServiceDetail to) throws SQLException, ClassNotFoundException {
        return null;
    }
}
