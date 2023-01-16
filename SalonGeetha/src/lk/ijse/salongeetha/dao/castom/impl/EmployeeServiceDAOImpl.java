package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.EmployeeServiceDAO;
import lk.ijse.salongeetha.to.EmployeeServiceDetailDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeServiceDAOImpl implements EmployeeServiceDAO {


    public boolean delete(EmployeeServiceDetailDTO employeeServiceDetailDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM employee_service_detail WHERE Emp_Id=? AND Sev_Id=?",
                employeeServiceDetailDTO.getEmpId(), employeeServiceDetailDTO.getSevId());
    }

    @Override
    public boolean checkAlreadyExists(EmployeeServiceDetailDTO employeeServiceDetailDTO) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM employee_service_detail WHERE Emp_Id=? AND Sev_Id=?",
                employeeServiceDetailDTO.getEmpId(), employeeServiceDetailDTO.getSevId());
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    public boolean add(EmployeeServiceDetailDTO employeeServiceDetailDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO employee_service_detail VALUES (?,?)", employeeServiceDetailDTO.getEmpId(),
                employeeServiceDetailDTO.getSevId());
    }


    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(EmployeeServiceDetailDTO to) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String checkId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet search(boolean value, EmployeeServiceDetailDTO to) throws SQLException, ClassNotFoundException {
        return null;
    }
}
