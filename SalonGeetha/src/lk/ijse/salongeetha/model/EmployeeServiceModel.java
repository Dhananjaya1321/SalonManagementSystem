package lk.ijse.salongeetha.model;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.to.EmployeeServiceDetail;
import lk.ijse.salongeetha.to.ProductServiceDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeServiceModel {


    public static ArrayList<EmployeeServiceDetail> getAllEmployeeServiceDetails() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeServiceDetail> employeeServiceDetails = new ArrayList<>();
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("select es.Emp_Id,es.Sev_Id,e.Name,s.Name from employee_service_detail es join \n" +
                        "service s on es.Sev_Id = s.Sev_Id join employee e on es.Emp_Id = e.Emp_Id;");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            EmployeeServiceDetail employeeServiceDetail=new EmployeeServiceDetail();
            employeeServiceDetail.setEmpId(String.valueOf(resultSet.getObject(1)));
            employeeServiceDetail.setSevId(String.valueOf(resultSet.getObject(2)));
            employeeServiceDetail.setEmpName(String.valueOf(resultSet.getObject(3)));
            employeeServiceDetail.setSevName(String.valueOf(resultSet.getObject(4)));
            employeeServiceDetails.add(employeeServiceDetail);
        }
        return employeeServiceDetails;
    }

    public static boolean deleteEmployeeService(EmployeeServiceDetail employeeServiceDetail) throws SQLException, ClassNotFoundException {
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("DELETE FROM employee_service_detail WHERE Emp_Id=? AND Sev_Id=?");
        prepareStatement.setObject(1,employeeServiceDetail.getEmpId());
        prepareStatement.setObject(2,employeeServiceDetail.getSevId());
        int executeUpdate = prepareStatement.executeUpdate();
        if (executeUpdate > 0) {
            return true;
        }
        return false;
    }

    public static boolean checkAlreadyExists(EmployeeServiceDetail employeeServiceDetail) throws SQLException, ClassNotFoundException {
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT * FROM employee_service_detail WHERE Emp_Id=? AND Sev_Id=?");
        prepareStatement.setObject(1,employeeServiceDetail.getEmpId());
        prepareStatement.setObject(2,employeeServiceDetail.getSevId());
        ResultSet resultSet = prepareStatement.executeQuery();
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    public static boolean addEmployeeService(EmployeeServiceDetail employeeServiceDetail) throws SQLException, ClassNotFoundException {
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("INSERT INTO employee_service_detail VALUES (?,?)");
        prepareStatement.setObject(1,employeeServiceDetail.getEmpId());
        prepareStatement.setObject(2,employeeServiceDetail.getSevId());
        boolean isAdded = prepareStatement.executeUpdate() > 0;
        if (isAdded) {
            return true;
        }
        return false;
    }
}
