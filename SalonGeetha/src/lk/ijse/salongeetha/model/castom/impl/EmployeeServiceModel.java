package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.to.EmployeeServiceDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeServiceModel {


    public static ArrayList<EmployeeServiceDetail> getAllEmployeeServiceDetails() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeServiceDetail> employeeServiceDetails = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("select es.Emp_Id,es.Sev_Id,e.Name,s.Name from employee_service_detail es join \n" +
                "service s on es.Sev_Id = s.Sev_Id join employee e on es.Emp_Id = e.Emp_Id;");
        while (resultSet.next()) {
            EmployeeServiceDetail employeeServiceDetail = new EmployeeServiceDetail();
            employeeServiceDetail.setEmpId(String.valueOf(resultSet.getObject(1)));
            employeeServiceDetail.setSevId(String.valueOf(resultSet.getObject(2)));
            employeeServiceDetail.setEmpName(String.valueOf(resultSet.getObject(3)));
            employeeServiceDetail.setSevName(String.valueOf(resultSet.getObject(4)));
            employeeServiceDetails.add(employeeServiceDetail);
        }
        return employeeServiceDetails;
    }

    public static boolean deleteEmployeeService(EmployeeServiceDetail employeeServiceDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM employee_service_detail WHERE Emp_Id=? AND Sev_Id=?",
                employeeServiceDetail.getEmpId(), employeeServiceDetail.getSevId());
    }

    public static boolean checkAlreadyExists(EmployeeServiceDetail employeeServiceDetail) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT * FROM employee_service_detail WHERE Emp_Id=? AND Sev_Id=?",
                employeeServiceDetail.getEmpId(), employeeServiceDetail.getSevId());
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    public static boolean addEmployeeService(EmployeeServiceDetail employeeServiceDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO employee_service_detail VALUES (?,?)", employeeServiceDetail.getEmpId(),
                employeeServiceDetail.getSevId());
    }
}
